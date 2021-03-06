package com.example.insta


import android.os.Parcel
import android.os.Parcelable

class News : Parcelable {
    var author: String? = null
    var title: String? = null
    var postData: String? = null
    var photoLink: String? = null
    var viewsCount: String? = null
    var commentsCount: String? = null


    var ava: String? = null
    var postPhoto: Int = 0
    var isPostLiked = false


    constructor(
            postData: String,
            author: String,
            viewsCount: String,
            title: String,
            commentsCount: String,
            photoLink: String,
            ava: String
    ) {
        this.photoLink = photoLink
        this.postData = postData
        this.author = author
        this.title = title
        this.viewsCount = viewsCount
        this.commentsCount = commentsCount
        this.ava = ava


    }


    override fun toString(): String {
        return "News{" +
                "author='" + author + '\''.toString() +
                ", title='" + title + '\''.toString() +
                ", postData='" + postData + '\''.toString() +
                ", viewsCount='" + viewsCount + '\''.toString() +
                ", commentsCount='" + commentsCount + '\''.toString() +
                ", ava='" + ava + '\''.toString() +
                ", postPhoto=" + postPhoto +
                '}'.toString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.author)
        dest.writeString(this.title)
        dest.writeString(this.viewsCount)
        dest.writeString(this.commentsCount)

        dest.writeString(this.ava)
        dest.writeInt(this.postPhoto)

        dest.writeString(this.postData)
        dest.writeString(this.photoLink)

    }

    private constructor(parcel: Parcel) {
        this.author = parcel.readString()
        this.title = parcel.readString()
        this.viewsCount = parcel.readString()
        this.commentsCount = parcel.readString()

        this.ava = parcel.readString()
        this.postPhoto = parcel.readInt()
        this.postData = parcel.readString()
        this.photoLink = parcel.readString()
    }

//    companion object {
//
//        val CREATOR: Parcelable.Creator<News> = object : Parcelable.Creator<News> {
//            override fun createFromParcel(source: Parcel): News {
//
//                return News(source)
//            }
//
//            override fun newArray(size: Int): Array<News> {
//
//                return arrayOfNulls(size)
//            }
//        }
//    }

    companion object CREATOR : Parcelable.Creator<News> {
            override fun createFromParcel(parcel: Parcel): News {
                return News(parcel)
            }

        override fun newArray(size: Int): Array<News?> {
            return arrayOfNulls(size)
        }
    }
}