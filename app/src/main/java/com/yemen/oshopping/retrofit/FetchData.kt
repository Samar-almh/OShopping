package com.yemen.oshopping.retrofit

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.yemen.oshopping.api.*
import com.yemen.oshopping.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


const val TAG = "fetchProduct"

class FetchData {


    fun fetchProduct(): LiveData<List<ProductItem>> {
        return fetchProductMetaData(RetrofitClient().oshoppingApi.fetchProduct())
    }

    fun fetchReportsDetails(block :Int ,checked:Int): LiveData<List<ReportsDetails>> {
        Log.d("RRTAG", "fetchReportsDetails: $block and $checked")
        val responseLiveData: MutableLiveData<List<ReportsDetails>> = MutableLiveData()
        var categoryRequest: Call<ReportsDetailsResponce> = RetrofitClient().oshoppingApi.fetchReportsDetails(block,checked)
        categoryRequest.enqueue(object : Callback<ReportsDetailsResponce> {

            override fun onFailure(call: Call<ReportsDetailsResponce>, t: Throwable) {
                Log.d(TAG, "Failed to fetch ReportDetails", t)
            }

            override fun onResponse(
                call: Call<ReportsDetailsResponce>,
                response: Response<ReportsDetailsResponce>
            ) {
                Log.d(TAG, "Response received successfully")
                val reportDetailsResponce: ReportsDetailsResponce? = response.body()
                val reportDetailsItems: List<ReportsDetails> = reportDetailsResponce?.reportDetailsItem
                    ?: mutableListOf()
                Log.d("TTTTAG", "onResponse: $reportDetailsItems")
                responseLiveData.value = reportDetailsItems
            }
        })
        return responseLiveData
    }

    fun fetchProductById(product_id: Int): LiveData<List<ProductItem>> {
        return fetchProductMetaData(RetrofitClient().oshoppingApi.fetchProductById(product_id))
    }
/*
    fun fetchProductById2(product_id: Int): LiveData<ProductItem> {


   // fun fetchProductById(product_id: Int): LiveData<ProductItem> {
        val responseLiveData: MutableLiveData<ProductItem> = MutableLiveData()
        val NewsRequest =
            RetrofitClient().oshoppingApi.fetchProductById(product_id)
        NewsRequest.enqueue(object : Callback<ProductResponse> {
            override fun onFailure(call: Call<SingleProductResponse>, t: Throwable) {
                Log.e("fetch Product details", "Failed to fetch product details", t)
            }

            override fun onResponse(
                call: Call<SingleProductResponse>,
                response: Response<SingleProductResponse>
            ) {
                Log.d(TAG, "Response received successfully")

                val singleProductResponse: SingleProductResponse? = response.body()
                val productItem: ProductItem? = singleProductResponse?.productItem
                responseLiveData.value = productItem

            }
        })

        return responseLiveData
    }

 */

    fun fetchUser(): LiveData<List<User>> {
        val responseLiveData: MutableLiveData<List<User>> = MutableLiveData()
        val UserRequest = RetrofitClient().oshoppingApi.fetchUser()
        UserRequest.enqueue(object : Callback<UserResponse> {
            override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                Log.d(TAG, "Failed to fetch Product", t)
            }

            override fun onResponse(
                call: Call<UserResponse>,
                response: Response<UserResponse>
            ) {
                Log.d(TAG, "Response received successfully")
                val userResponse: UserResponse? = response.body()
                val user: List<User> = userResponse?.userItem
                    ?: mutableListOf()
                responseLiveData.value = user
                Log.d("onResponse", "onResponse: $user")
            }
        })
        return responseLiveData
    }

    fun fetchCart(user_id: Int): LiveData<List<Cart>> {
        val responseLiveData: MutableLiveData<List<Cart>> = MutableLiveData()
        val cartRequest =
            RetrofitClient().oshoppingApi.fetchCart(user_id)
        cartRequest.enqueue(object : Callback<CartResponse> {
            override fun onFailure(call: Call<CartResponse>, t: Throwable) {
                Log.e("retrofit_fetchcart ", "Failed to fetch cart", t)
            }

            override fun onResponse(
                call: Call<CartResponse>,
                response: Response<CartResponse>
            ) {
                Log.d("retrofit_fetchcart", "Response received successfully")

                val cartResponse: CartResponse? = response.body()
                val cart: List<Cart> = cartResponse?.cartItem ?: mutableListOf()
                responseLiveData.value = cart

            }
        })

        return responseLiveData
    }


/*
    fun fetchCategory(): LiveData<List<Category>> {
        val responseLiveData: MutableLiveData<List<Category>> = MutableLiveData()
        var categoryRequest: Call<CategoryResponse> = RetrofitClient().oshoppingApi.fetchCategory()
        categoryRequest.enqueue(object : Callback<CategoryResponse> {

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                Log.d(TAG, "Failed to fetch Product", t)
                */

    fun fetchUserById(user_id: Int): LiveData<User> {
        Log.d("TAGdd", "fetchUserById: ss")
        val responseLiveData: MutableLiveData<User> = MutableLiveData()
        val userRequest =
            RetrofitClient().oshoppingApi.fetchUserById(user_id)
        userRequest.enqueue(object : Callback<SingleUserResponse> {
            override fun onFailure(call: Call<SingleUserResponse>, t: Throwable) {
                Log.e("fetch user details", "Failed to fetch user details", t)

            }

            override fun onResponse(
                call: Call<SingleUserResponse>,
                response: Response<SingleUserResponse>
            ) {
                val singleUserResponse: SingleUserResponse? = response.body()
                val user: User? = singleUserResponse?.user
                responseLiveData.value = user

            }
        })

        return responseLiveData
    }
    fun fetchReportDetailsByUserId(against: Int): LiveData<List<ReportDetails>> {
        Log.d("TAGdd", "fetchUserById: ss $against")
        val responseLiveData: MutableLiveData<List<ReportDetails>> = MutableLiveData()
        val NewsRequest =
            RetrofitClient().oshoppingApi.fetchReportDetailsByUserId(against)
        NewsRequest.enqueue(object : Callback<ReportDetailsResponce> {
            override fun onFailure(call: Call<ReportDetailsResponce>, t: Throwable) {
                Log.e("fetch user details", "Failed to fetch  reportDetails", t)

            }

            override fun onResponse(
                call: Call<ReportDetailsResponce>,
                response: Response<ReportDetailsResponce>
            ) {
                val reportDetailsResponce: ReportDetailsResponce? = response.body()
                val reportDetails: List<ReportDetails>? = reportDetailsResponce?.reportDetailsItem
                responseLiveData.value = reportDetails
                Log.d("onResponseeeee", "onResponse: $reportDetails")

            }
        })

        return responseLiveData
    }

    fun fetchProductReportDetailsByProductId(product_id: Int): LiveData<List<ProductReportDetailsF>> {
        val responseLiveData: MutableLiveData<List<ProductReportDetailsF>> = MutableLiveData()
        val NewsRequest =
            RetrofitClient().oshoppingApi.fetchProductReportByProductId(product_id)
        NewsRequest.enqueue(object : Callback<ProductReportDetailsResponse> {
            override fun onFailure(call: Call<ProductReportDetailsResponse>, t: Throwable) {
                Log.e("fetch product reports", "Failed to fetch product report Details", t)

            }

            override fun onResponse(
                call: Call<ProductReportDetailsResponse>,
                response: Response<ProductReportDetailsResponse>
            ) {
                val productReportDetailsResponce: ProductReportDetailsResponse? = response.body()
                val productReportDetails: List<ProductReportDetailsF>? = productReportDetailsResponce?.productReportItem
                responseLiveData.value = productReportDetails
                Log.d("onResponse", "onResponse:gfgfgfg $productReportDetails")

            }
        })

        return responseLiveData
    }
    fun fetchProductByCategory(category_id: Int): LiveData<List<ProductItem>> {
        return fetchProductMetaData(RetrofitClient().oshoppingApi.fetchProductByCategory(category_id))
    }

    fun fetchProductByVendorId(vendorId: Int): LiveData<List<ProductItem>> {
        return fetchProductMetaData(RetrofitClient().oshoppingApi.fetchProductByVendorId(vendorId))
    }
    fun fetchProductByHide(hide: Int): LiveData<List<ProductItem>> {
        return fetchProductMetaData(RetrofitClient().oshoppingApi.fetchProductByVendorId(hide))
    }

    fun searchProduct(query: String): LiveData<List<ProductItem>> {
        return fetchProductMetaData(RetrofitClient().oshoppingApi.searchProduct(query))
    }
    fun fetchProductByColor(color: String): LiveData<List<ProductItem>> {
        return fetchProductMetaData(RetrofitClient().oshoppingApi.fetchProductByColor(color))
    }

    fun fetchProductMetaData(productRequest: Call<ProductResponse>): LiveData<List<ProductItem>> {

        val responseLiveData: MutableLiveData<List<ProductItem>> = MutableLiveData()

        productRequest.enqueue(object : Callback<ProductResponse> {

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.d(TAG, "Failed to fetch Product", t)
            }

            override fun onResponse(
                call: Call<ProductResponse>,
                response: Response<ProductResponse>
            ) {
                Log.d(TAG, "Response received successfully")
                val productResponse: ProductResponse? = response.body()
                val productItems: List<ProductItem> = productResponse?.productItem
                    ?: mutableListOf()
                responseLiveData.value = productItems
                Log.d("onResponse", "onResponse: $productItems")
            }
        })

        return responseLiveData
    }


    fun fetchCategory(): LiveData<List<Category>> {
        val responseLiveData: MutableLiveData<List<Category>> = MutableLiveData()
        var categoryRequest: Call<CategoryResponse> = RetrofitClient().oshoppingApi.fetchCategory()
        categoryRequest.enqueue(object : Callback<CategoryResponse> {

            override fun onFailure(call: Call<CategoryResponse>, t: Throwable) {
                Log.d(TAG, "Failed to fetch Category", t)
            }

            override fun onResponse(
                call: Call<CategoryResponse>,
                response: Response<CategoryResponse>
            ) {
                Log.d(TAG, "Response received successfully")
                val categoryResponse: CategoryResponse? = response.body()
                val categoryItems: List<Category> = categoryResponse?.categoryItem
                    ?: mutableListOf()

                responseLiveData.value = categoryItems
            }
        })
        return responseLiveData
    }
        fun fetchProductReportsDetails(hide:Int ,checked:Int): LiveData<List<ProductReportsDetailsF>> {
            val responseLiveData: MutableLiveData<List<ProductReportsDetailsF>> = MutableLiveData()
            var categoryRequest: Call<ProductReportsDetailsResponse> =
                RetrofitClient().oshoppingApi.fetchProductReportsDetails(hide,checked)
            categoryRequest.enqueue(object : Callback<ProductReportsDetailsResponse> {

                override fun onFailure(call: Call<ProductReportsDetailsResponse>, t: Throwable) {
                    Log.d(TAG, "Failed to fetch Category", t)
                }

                override fun onResponse(
                    call: Call<ProductReportsDetailsResponse>,
                    response: Response<ProductReportsDetailsResponse>
                ) {
                    Log.d(TAG, "Response received successfully")
                    val productReportsDetails: ProductReportsDetailsResponse? = response.body()
                    val productReports: List<ProductReportsDetailsF> =
                        productReportsDetails?.productReportsItem
                            ?: mutableListOf()
                    Log.d("HHH", "Response received successfully$productReports")
                    responseLiveData.value = productReports
                }
            })

            return responseLiveData
        }

        fun fetchReport(): LiveData<List<Report>> {
            val responseLiveData: MutableLiveData<List<Report>> = MutableLiveData()
            val reportRequest: Call<ReportResponse> = RetrofitClient().oshoppingApi.fetchReport()
            reportRequest.enqueue(object : Callback<ReportResponse> {

                override fun onFailure(call: Call<ReportResponse>, t: Throwable) {
                    Log.d("fetchReport", "Failed to fetch Reports", t)
                }

                override fun onResponse(
                    call: Call<ReportResponse>,
                    response: Response<ReportResponse>
                ) {
                    Log.d("fetchReport", "Reports received successfully")
                    val reportResponse: ReportResponse? = response.body()
                    val reportItems: List<Report> = reportResponse?.reportItem
                        ?: mutableListOf()
                    responseLiveData.value = reportItems
                }
            })

            return responseLiveData
        }


        fun fetchActivitiesMetaData(activityRequest: Call<ActivityResponse>): LiveData<List<ActivityItem>> {

            val responseLiveData: MutableLiveData<List<ActivityItem>> = MutableLiveData()

            activityRequest.enqueue(object : Callback<ActivityResponse> {

                override fun onFailure(call: Call<ActivityResponse>, t: Throwable) {
                    Log.d("fetchActivity", "Failed to fetch Product", t)
                }

                override fun onResponse(
                    call: Call<ActivityResponse>,
                    response: Response<ActivityResponse>
                ) {
                    Log.d("fetchActivity", "Response received successfully")
                    val ActivityResponse: ActivityResponse? = response.body()
                    val activityItems: List<ActivityItem> = ActivityResponse?.activItem
                        ?: mutableListOf()
                    responseLiveData.value = activityItems
                    Log.d("fetchActivity", "onResponse: $activityItems")
                }
            })
            return responseLiveData
        }


        //fetch users
        fun fetchUsers(block:Int): LiveData<List<User>> {
            return fetchUserMetaData(RetrofitClient().oshoppingApi.fetchUsers(block))
        }

        fun fetchUserByEmail(email: String): LiveData<List<User>> {
            return fetchUserMetaData(RetrofitClient().oshoppingApi.fetchUserByEmail(email))
        }

        fun fetchUserMetaData(userRequest: Call<UserResponse>): LiveData<List<User>> {
            val responseLiveData: MutableLiveData<List<User>> = MutableLiveData()

            userRequest.enqueue(object : Callback<UserResponse> {

                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
                    Log.d("fetchReport", "Failed to fetch Reports", t)
                }

                override fun onResponse(
                    call: Call<UserResponse>,
                    response: Response<UserResponse>
                ) {
                    Log.d("fetchReport", "Reports received successfully")
                    val userResponse: UserResponse? = response.body()
                    val userItems: List<User> = userResponse?.userItem
                        ?: mutableListOf()
                    responseLiveData.value = userItems

                }
            })

            return responseLiveData
        }


        fun fetchActivity(user_id: Int): LiveData<List<ActivityItem>> {
            return fetchActivitiesMetaData(RetrofitClient().oshoppingApi.fetchActivities(user_id))
        }


    }