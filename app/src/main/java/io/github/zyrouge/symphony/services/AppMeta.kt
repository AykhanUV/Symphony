package app.uvteam.symphony.services

import app.uvteam.symphony.BuildConfig
import app.uvteam.symphony.utils.HttpClient
import app.uvteam.symphony.utils.Logger
import okhttp3.CacheControl
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

object AppMeta {
    const val appName = "Symphony"
    const val author = "AykhanUV"
    const val githubRepositoryOwner = "AykhanUV"
    const val githubRepositoryName = "Symphony"
    const val githubProfileUrl = "https://github.com/$githubRepositoryOwner"
    const val githubRepositoryUrl =
        "https://github.com/$githubRepositoryOwner/$githubRepositoryName"

    const val version = "v${BuildConfig.VERSION_NAME}"
    var latestVersion: String? = null
    const val githubLatestReleaseUrl = "$githubRepositoryUrl/releases/latest"
    const val githubIssuesUrl = "$githubRepositoryUrl/issues"
    const val discordUrl = ""
    const val redditUrl = ""
    const val contributingUrl = "$githubRepositoryUrl#contributing"

    const val packageName = "app.uvteam.symphony"
    const val izzyOnDroidUrl = "https://apt.izzysoft.de/fdroid/index/apk/$packageName"
    const val fdroidUrl = "https://f-droid.org/en/packages/$packageName"

    fun isNightlyBuild() = version.contains("-nightly")

    fun fetchLatestVersion() = when {
        isNightlyBuild() -> fetchLatestNightlyVersion()
        else -> fetchLatestStableVersion()
    }

    fun fetchLatestStableVersion(): String? {
        try {
            val latestReleaseUrl =
                "https://api.github.com/repos/$githubRepositoryOwner/$githubRepositoryName/releases/latest"
            val req = Request.Builder()
                .url(latestReleaseUrl)
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build()
            val res = HttpClient.newCall(req).execute()
            val content = res.body?.string() ?: ""
            val json = JSONObject(content)
            val tagName = json.getString("tag_name")
            val draft = json.getBoolean("draft")
            if (!draft) {
                latestVersion = tagName
            }
        } catch (err: Exception) {
            Logger.warn("AppMeta", "version check failed: $err")
        }
        return latestVersion
    }

    fun fetchLatestNightlyVersion(): String? {
        try {
            val latestReleaseUrl =
                "https://api.github.com/repos/$githubRepositoryOwner/$githubRepositoryName/releases"
            val req = Request.Builder()
                .url(latestReleaseUrl)
                .cacheControl(CacheControl.FORCE_NETWORK)
                .build()
            val res = HttpClient.newCall(req).execute()
            val content = res.body?.string() ?: ""
            val json = JSONArray(content)
            for (i in 0 until json.length()) {
                val x = json.getJSONObject(i)
                val tagName = x.getString("tag_name")
                val prerelease = x.getBoolean("prerelease")
                if (prerelease) {
                    latestVersion = tagName
                    break
                }
            }
        } catch (err: Exception) {
            Logger.warn("AppMeta", "nightly version check failed: $err")
        }
        return latestVersion
    }
}
