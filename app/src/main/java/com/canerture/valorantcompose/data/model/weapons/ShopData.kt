package com.canerture.valorantcompose.data.model.weapons

import com.google.gson.annotations.SerializedName

data class ShopData(
    @SerializedName("assetPath")
    val assetPath: String,
    @SerializedName("canBeTrashed")
    val canBeTrashed: Boolean,
    @SerializedName("category")
    val category: String,
    @SerializedName("categoryText")
    val categoryText: String,
    @SerializedName("cost")
    val cost: Int,
    @SerializedName("gridPosition")
    val gridPosition: GridPosition,
    @SerializedName("image")
    val image: Any,
    @SerializedName("newImage")
    val newImage: String,
    @SerializedName("newImage2")
    val newImage2: Any
)