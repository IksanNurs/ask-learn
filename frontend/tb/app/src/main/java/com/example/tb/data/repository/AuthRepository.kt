package com.example.tbsewaku.data.repository

import com.example.tbsewaku.data.api.ApiService
import com.example.tbsewaku.data.preferences.SharedPrefsManager
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File

class AuthRepository(
    private val apiService: ApiService,
    private val sharedPrefsManager: SharedPrefsManager
) {

    // Fungsi login tanpa model
    suspend fun login(username: String, password: String): Boolean {
        val response = apiService.login(mapOf("username" to username, "password" to password))

        if (response.isSuccessful) {
            val responseBody = response.body()
             println("DEBUG: Raw Response: ${response.raw()}")
            println("DEBUG: Response Code: ${response.code()}")
            println("DEBUG: Response Body: ${response.body()}")
            val message = responseBody?.get("message") as? String
                val data = responseBody?.get("data") as? Map<String, Any>
                val token = data?.get("token") as? String
                val user = data?.get("user") as? Map<String, Any>
                   println("DEBUG: Response usery: ${user}")
                   println("DEBUG: Response token: ${token}")
                if (token != null && user != null) {
                    // Simpan token dan data user
                    val userId = (user["id"] as Double).toInt()
                    val username = user["username"] as? String
                    println("DEBUG: Response username: ${username}")
                    println("DEBUG: Response userid: ${userId}")
                    if (userId != null && username != null) {
                        sharedPrefsManager.saveLoginData(token, userId, username)
                        return true
                    }
                }
        }
        return false
    }

    suspend fun register(email: String, username: String, password: String): Boolean {
        return try {
            val response = apiService.register(mapOf(
                "email" to email,
                "username" to username,
                "password" to password
            ))
            
            println("DEBUG: Register Raw Response: ${response.raw()}")
            println("DEBUG: Register Response Code: ${response.code()}")
            println("DEBUG: Register Response Body: ${response.body()}")
            
            if (response.isSuccessful && response.body() != null) {
                val responseBody = response.body()!!
                val data = responseBody["data"] as Map<*, *>
                val user = data["user"] as Map<*, *>
                val token = data["token"] as String
                val userId = (user["id"] as Double).toInt()
                val username = user["username"] as String
                
                println("DEBUG: Register Token: $token")
                println("DEBUG: Register UserId: $userId")
                println("DEBUG: Register Username: $username")
                
                sharedPrefsManager.saveLoginData(token, userId, username)
                true
            } else {
                println("DEBUG: Register failed")
                false
            }
        } catch (e: Exception) {
            println("DEBUG: Register exception - ${e.message}")
            e.printStackTrace()
            false
        }
    }

    suspend fun getUserSelf(token: String): Map<String, Any>? {
    try {
        val response = apiService.getUserSelf("Bearer $token")
        if (response.isSuccessful) {
            val responseBody = response.body()
            val data = responseBody?.get("data") as? Map<String, Any>
            return data
        }
    } catch (e: Exception) {
        println("DEBUG: GetUserSelf exception - ${e.message}")
    }
    return null
}

suspend fun updateUser(
    token: String,
    username: String,
    email: String,
    address: String?,
    latitude: String?,
    longitude: String?
): Boolean {
    try {
        val requestBody = mapOf(
            "username" to username,
            "email" to email,
            "address" to (address ?: ""),
            "latitude" to (latitude ?: ""),
            "longitude" to (longitude ?: "")
        )

        val response = apiService.updateUser(
            body = requestBody,
            token = "Bearer $token"
        )
        return response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: UpdateUser exception - ${e.message}")
        return false
    }
}

suspend fun createProduct(
    token: String,
    name: String,
    description: String,
    type: String, 
    price: String,
    stock: String,
    imageFile: File
): Boolean {
    return try {
        // Create image part with specific content type
        val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), imageFile)
        val imagePart = MultipartBody.Part.createFormData("image", imageFile.name, requestFile)
        
        // Create text parts
        val namePart = RequestBody.create("text/plain".toMediaTypeOrNull(), name)
        val descriptionPart = RequestBody.create("text/plain".toMediaTypeOrNull(), description)
        val typePart = RequestBody.create("text/plain".toMediaTypeOrNull(), type)
        val pricePart = RequestBody.create("text/plain".toMediaTypeOrNull(), price)
        val stockPart = RequestBody.create("text/plain".toMediaTypeOrNull(), stock)

        val response = apiService.createProduct(
            name = namePart,
            description = descriptionPart,
            type = typePart,
            price = pricePart,
            stock = stockPart,
            image = imagePart,
            token = "Bearer $token"
        )

        println("DEBUG: CreateProduct response code - ${response.code()}")
        println("DEBUG: CreateProduct raw response - ${response.raw()}")
        
        response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: CreateProduct exception - ${e.message}")
        e.printStackTrace()
        false
    }
}



suspend fun getOrders(token: String, status: Int? = null): List<Map<String, Any>>? {
    try {
        val response = apiService.getOrders("Bearer $token", status)
        if (response.isSuccessful) {
            val responseBody = response.body()
            val data = responseBody?.get("data") as? List<Map<String, Any>>
            return data
        }
    } catch (e: Exception) {
        println("DEBUG: GetOrders exception - ${e.message}")
    }
    return null
}


suspend fun getProducts(token: String, name: String? = null, sort: String? = null): List<Map<String, Any>>? {
    try {
        val response = apiService.getProducts(name, sort, "Bearer $token")
        if (response.isSuccessful) {
            val responseBody = response.body()
            val data = responseBody?.get("data") as? Map<String, Any>
            val products = data?.get("products") as? List<Map<String, Any>>
            return products
        }
    } catch (e: Exception) {
        println("DEBUG: GetProducts exception - ${e.message}")
    }
    return null
}

suspend fun updateOrderStatus(token: String, orderId: Int, status: Int): Boolean {
    try {
        val response = apiService.updateOrder(
            orderId = orderId,
            body = mapOf("status" to status),
            token = "Bearer $token"
        )
        return response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: UpdateOrder exception - ${e.message}")
        return false
    }
}

suspend fun updateOrderRate(token: String, orderId: Int, rate: Int): Boolean {
    try {
        val response = apiService.updateOrder(
            orderId = orderId,
            body = mapOf("rate" to rate),
            token = "Bearer $token"
        )
        return response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: UpdateRate exception - ${e.message}")
        return false
    }
}

suspend fun createCategory(token: String, name: String): Boolean {
    return try {
        val response = apiService.createCategory(
            body = mapOf("name" to name),
            token = "Bearer $token"
        )
        response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: CreateCategory exception - ${e.message}")
        false
    }
}

suspend fun getCategories(token: String): List<Map<String, Any>>? {
    return try {
        val response = apiService.getCategories("Bearer $token")
        if (response.isSuccessful) {
            val responseBody = response.body()
            val data = responseBody?.get("data") as? List<Map<String, Any>>
            data
        } else null
    } catch (e: Exception) {
        println("DEBUG: GetCategories exception - ${e.message}")
        null
    }
}

suspend fun getCategoryById(token: String, categoryId: Int): Map<String, Any>? {
    return try {
        val response = apiService.getCategoryById(categoryId, "Bearer $token")
        if (response.isSuccessful) {
            val responseBody = response.body()
            val data = responseBody?.get("data") as? Map<String, Any>
            data
        } else null
    } catch (e: Exception) {
        println("DEBUG: GetCategoryById exception - ${e.message}")
        null
    }
}

suspend fun updateCategory(token: String, categoryId: Int, name: String): Boolean {
    return try {
        val response = apiService.updateCategory(
            categoryId = categoryId,
            body = mapOf("name" to name),
            token = "Bearer $token"
        )
        response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: UpdateCategory exception - ${e.message}")
        false
    }
}

suspend fun deleteCategory(token: String, categoryId: Int): Boolean {
    return try {
        val response = apiService.deleteCategory(
            categoryId = categoryId,
            token = "Bearer $token"
        )
        response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: DeleteCategory exception - ${e.message}")
        false
    }
}

suspend fun createClass(
    token: String,
    quota: String,
    subject: String,
    topic: String,
    location: String,
    khsFile: File?
): Boolean {
    return try {
        // Create RequestBody parts
        val quotaPart = RequestBody.create("text/plain".toMediaTypeOrNull(), quota)
        val subjectPart = RequestBody.create("text/plain".toMediaTypeOrNull(), subject)
        val topicPart = RequestBody.create("text/plain".toMediaTypeOrNull(), topic)
        val locationPart = RequestBody.create("text/plain".toMediaTypeOrNull(), location)

        // Create KHS part if file exists
        val khsPart = khsFile?.let {
            val requestFile = RequestBody.create("application/pdf".toMediaTypeOrNull(), it)
            MultipartBody.Part.createFormData("khs", it.name, requestFile)
        }

        val response = apiService.createClass(
            quota = quotaPart,
            subject = subjectPart,
            topic = topicPart,
            location = locationPart,
            khs = khsPart,
            token = "Bearer $token"
        )

        println("DEBUG: CreateClass response code - ${response.code()}")
        response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: CreateClass exception - ${e.message}")
        false
    }
}

suspend fun getClasses(token: String): List<Map<String, Any>>? {
    return try {
        val response = apiService.getClasses("Bearer $token")
        if (response.isSuccessful) {
            val responseBody = response.body()
            val data = responseBody?.get("data") as? List<Map<String, Any>>
            data
        } else null
    } catch (e: Exception) {
        println("DEBUG: GetClasses exception - ${e.message}")
        null
    }
}

suspend fun getClassById(token: String, classId: Int): Map<String, Any>? {
    return try {
        val response = apiService.getClassById(classId, "Bearer $token")
        if (response.isSuccessful) {
            val responseBody = response.body()
            val data = responseBody?.get("data") as? Map<String, Any>
            data
        } else null
    } catch (e: Exception) {
        println("DEBUG: GetClassById exception - ${e.message}")
        null
    }
}

suspend fun updateClass(
    token: String,
    classId: Int,
    quota: String?,
    subject: String?,
    topic: String?,
    location: String?,
    khsFile: File?
): Boolean {
    return try {
        // Create RequestBody parts for non-null values
        val quotaPart = quota?.let { RequestBody.create("text/plain".toMediaTypeOrNull(), it) }
        val subjectPart = subject?.let { RequestBody.create("text/plain".toMediaTypeOrNull(), it) }
        val topicPart = topic?.let { RequestBody.create("text/plain".toMediaTypeOrNull(), it) }
        val locationPart = location?.let { RequestBody.create("text/plain".toMediaTypeOrNull(), it) }

        // Create KHS part if file exists
        val khsPart = khsFile?.let {
            val requestFile = RequestBody.create("application/pdf".toMediaTypeOrNull(), it)
            MultipartBody.Part.createFormData("khs", it.name, requestFile)
        }

        val response = apiService.updateClass(
            classId = classId,
            quota = quotaPart,
            subject = subjectPart,
            topic = topicPart,
            location = locationPart,
            khs = khsPart,
            token = "Bearer $token"
        )

        response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: UpdateClass exception - ${e.message}")
        false
    }
}

suspend fun deleteClass(token: String, classId: Int): Boolean {
    return try {
        val response = apiService.deleteClass(
            classId = classId,
            token = "Bearer $token"
        )
        response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: DeleteClass exception - ${e.message}")
        false
    }
}

suspend fun createTutor(token: String, name: String, classId: Int): Boolean {
    return try {
        val response = apiService.createTutor(
            body = mapOf(
                "name" to name,
                "class_id" to classId
            ),
            token = "Bearer $token"
        )
        println("DEBUG: CreateTutor response code - ${response.code()}")
        response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: CreateTutor exception - ${e.message}")
        false
    }
}

suspend fun getTutors(token: String): List<Map<String, Any>>? {
    return try {
        val response = apiService.getTutors("Bearer $token")
        if (response.isSuccessful) {
            val responseBody = response.body()
            val data = responseBody?.get("data") as? List<Map<String, Any>>
            data
        } else null
    } catch (e: Exception) {
        println("DEBUG: GetTutors exception - ${e.message}")
        null
    }
}

suspend fun getTutorById(token: String, tutorId: Int): Map<String, Any>? {
    return try {
        val response = apiService.getTutorById(tutorId, "Bearer $token")
        if (response.isSuccessful) {
            val responseBody = response.body()
            val data = responseBody?.get("data") as? Map<String, Any>
            data
        } else null
    } catch (e: Exception) {
        println("DEBUG: GetTutorById exception - ${e.message}")
        null
    }
}

suspend fun updateTutor(
    token: String, 
    tutorId: Int, 
    name: String? = null, 
    classId: Int? = null
): Boolean {
    return try {
        val updateData = mutableMapOf<String, Any>()
        name?.let { updateData["name"] = it }
        classId?.let { updateData["class_id"] = it }

        val response = apiService.updateTutor(
            tutorId = tutorId,
            body = updateData,
            token = "Bearer $token"
        )
        println("DEBUG: UpdateTutor response code - ${response.code()}")
        response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: UpdateTutor exception - ${e.message}")
        false
    }
}

suspend fun deleteTutor(token: String, tutorId: Int): Boolean {
    return try {
        val response = apiService.deleteTutor(
            tutorId = tutorId,
            token = "Bearer $token"
        )
        println("DEBUG: DeleteTutor response code - ${response.code()}")
        response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: DeleteTutor exception - ${e.message}")
        false
    }
}

suspend fun createItem(
    token: String,
    description: String,
    imageFile: File?
): Boolean {
    return try {
        // Create description part
        val descriptionPart = RequestBody.create("text/plain".toMediaTypeOrNull(), description)

        // Create image part if file exists
        val imagePart = imageFile?.let {
            val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), it)
            MultipartBody.Part.createFormData("image", it.name, requestFile)
        }

        val response = apiService.createItem(
            description = descriptionPart,
            image = imagePart,
            token = "Bearer $token"
        )

        println("DEBUG: CreateItem response code - ${response.code()}")
        response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: CreateItem exception - ${e.message}")
        false
    }
}

suspend fun getItems(token: String): List<Map<String, Any>>? {
    return try {
        val response = apiService.getItems("Bearer $token")
        if (response.isSuccessful) {
            val responseBody = response.body()
            val data = responseBody?.get("data") as? List<Map<String, Any>>
            data
        } else null
    } catch (e: Exception) {
        println("DEBUG: GetItems exception - ${e.message}")
        null
    }
}

suspend fun getItemById(token: String, itemId: Int): Map<String, Any>? {
    return try {
        val response = apiService.getItemById(itemId, "Bearer $token")
        if (response.isSuccessful) {
            val responseBody = response.body()
            val data = responseBody?.get("data") as? Map<String, Any>
            data
        } else null
    } catch (e: Exception) {
        println("DEBUG: GetItemById exception - ${e.message}")
        null
    }
}

suspend fun updateItem(
    token: String,
    itemId: Int,
    description: String?,
    imageFile: File?
): Boolean {
    return try {
        // Create description part if not null
        val descriptionPart = description?.let {
            RequestBody.create("text/plain".toMediaTypeOrNull(), it)
        }

        // Create image part if file exists
        val imagePart = imageFile?.let {
            val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), it)
            MultipartBody.Part.createFormData("image", it.name, requestFile)
        }

        val response = apiService.updateItem(
            itemId = itemId,
            description = descriptionPart,
            image = imagePart,
            token = "Bearer $token"
        )

        println("DEBUG: UpdateItem response code - ${response.code()}")
        response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: UpdateItem exception - ${e.message}")
        false
    }
}

suspend fun deleteItem(token: String, itemId: Int): Boolean {
    return try {
        val response = apiService.deleteItem(
            itemId = itemId,
            token = "Bearer $token"
        )
        println("DEBUG: DeleteItem response code - ${response.code()}")
        response.isSuccessful
    } catch (e: Exception) {
        println("DEBUG: DeleteItem exception - ${e.message}")
        false
    }
}




}