package ru.kanogor.abra_kadabra.retrofit


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserData(
    @Json(name = "results") val results: List<ResultsData>,
    @Json(name = "info") val info: InfoData
)

@JsonClass(generateAdapter = true)
data class ResultsData(
    @Json(name = "gender") val gender: String?,
    @Json(name = "name") val name: Name,
    @Json(name = "location") val location: Location,
    @Json(name = "email") val email: String,
    @Json(name = "login") val login: Login,
    @Json(name = "dob") val dob: DateAge,
    @Json(name = "registered") val registered: DateAge,
    @Json(name = "phone") val phone: String,
    @Json(name = "cell") val cell: String,
    @Json(name = "id") val id: IdValue,
    @Json(name = "picture") val picture: PictureSize,
    @Json(name = "nat") val nat: String
)

@JsonClass(generateAdapter = true)
data class Name(
    @Json(name = "title") val title: String,
    @Json(name = "first") val first: String,
    @Json(name = "last") val last: String,
)

@JsonClass(generateAdapter = true)
data class Location(
    @Json(name = "street") val street: Street,
    @Json(name = "city") val city: String,
    @Json(name = "state") val state: String,
    @Json(name = "country") val country: String,
    @Json(name = "postcode") val postcode: String,
    @Json(name = "coordinates") val coordinates: Coordinates,
    @Json(name = "timezone") val timezone: Timezone
)

@JsonClass(generateAdapter = true)
data class Coordinates(
    @Json(name = "latitude") val latitude: String,
    @Json(name = "longitude") val longitude: String
)

@JsonClass(generateAdapter = true)
data class Timezone(
    @Json(name = "offset") val offset: String,
    @Json(name = "description") val description: String
)

@JsonClass(generateAdapter = true)
data class Street(
    @Json(name = "number") val number: Int,
    @Json(name = "name") val name: String
)

@JsonClass(generateAdapter = true)
data class Login(
    @Json(name = "uuid") val uuid: String,
    @Json(name = "username") val username: String,
    @Json(name = "password") val password: String,
    @Json(name = "salt") val salt: String,
    @Json(name = "md5") val md5: String,
    @Json(name = "sha1") val sha1: String,
    @Json(name = "sha256") val sha256: String
)

@JsonClass(generateAdapter = true)
data class DateAge(
    @Json(name = "date") val date: String,
    @Json(name = "age") val age: Int
)

@JsonClass(generateAdapter = true)
data class IdValue(
    @Json(name = "name") val name: String,
    @Json(name = "value") val value: String?
)

@JsonClass(generateAdapter = true)
data class PictureSize(
    @Json(name = "large") val large: String,
    @Json(name = "medium") val medium: String,
    @Json(name = "thumbnail") val thumbnail: String
)

@JsonClass(generateAdapter = true)
data class InfoData(
    @Json(name = "seed") val seed: String,
    @Json(name = "results") val results: Int,
    @Json(name = "page") val page: Int,
    @Json(name = "version") val version: String
)

