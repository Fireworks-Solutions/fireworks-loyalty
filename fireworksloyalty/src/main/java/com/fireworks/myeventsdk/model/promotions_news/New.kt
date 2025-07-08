package digital.fireworks.kpdrm.data.dto.promotions_news

data class New(
    val category: String,
    val created_at: String,
    val description: String,
    val end_date: String,
    val end_date_text: String,
    val featured_img: String,
    val id: String,
    val link: String,
    val location: String,
    val mall: Int,
    val open_link: Boolean,
    val start_date: String,
    val title: String,
    val clickable: Boolean
)