import kotlin.time.Duration

fun formatTime(duration: Duration): String {
    val minutes = duration.inWholeMinutes
    val seconds = duration.inWholeSeconds % 60
    return "%02d:%02d".format(minutes, seconds)
}
