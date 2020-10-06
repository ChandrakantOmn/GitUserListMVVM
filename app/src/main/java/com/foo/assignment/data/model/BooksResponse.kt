package com.foo.assignment.data.model


import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class BooksResponse(
    var items: ArrayList<Item?>?,
    var kind: String?, // books#volumes
    var totalItems: Int? // 3138
) {
    data class Item(
        var accessInfo: AccessInfo?,
        var etag: String?, // nVS0KWFjOXU
        var id: String?, // I8gDAAAAMBAJ
        var kind: String?, // books#volume
        var saleInfo: SaleInfo?,
        var searchInfo: SearchInfo?,
        var selfLink: String?, // https://www.googleapis.com/books/v1/volumes/I8gDAAAAMBAJ
        var volumeInfo: VolumeInfo?
    ):Parcelable {
        constructor(parcel: Parcel) : this(
            parcel.readParcelable(AccessInfo::class.java.classLoader),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(SaleInfo::class.java.classLoader),
            parcel.readParcelable(SearchInfo::class.java.classLoader),
            parcel.readString(),
            parcel.readParcelable(VolumeInfo::class.java.classLoader)
        ) {
        }

        data class AccessInfo(
            var accessViewStatus: String?, // SAMPLE
            var country: String?, // IN
            var embeddable: Boolean?, // true
            var epub: Epub?,
            var pdf: Pdf?,
            var publicDomain: Boolean?, // false
            var quoteSharingAllowed: Boolean?, // false
            var textToSpeechPermission: String?, // ALLOWED
            var viewability: String?, // PARTIAL
            var webReaderLink: String? // http://play.google.com/books/reader?id=zoHxNzbQa8MC&hl=&printsec=frontcover&source=gbs_api
        ) :Parcelable{
            constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
                parcel.readParcelable(Epub::class.java.classLoader),
                parcel.readParcelable(Pdf::class.java.classLoader),
                parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
                parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
                parcel.readString(),
                parcel.readString(),
                parcel.readString()
            ) {
            }

            data class Epub(
                var acsTokenLink: String?, // http://books.google.co.in/books/download/Health_Care_Policy-sample-epub.acsm?id=UGiwtqSJVwsC&format=epub&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api
                var isAvailable: Boolean? // false
            ):Parcelable {
                constructor(parcel: Parcel) : this(
                    parcel.readString(),
                    parcel.readValue(Boolean::class.java.classLoader) as? Boolean
                ) {
                }

                override fun writeToParcel(parcel: Parcel, flags: Int) {
                    parcel.writeString(acsTokenLink)
                    parcel.writeValue(isAvailable)
                }

                override fun describeContents(): Int {
                    return 0
                }

                companion object CREATOR : Parcelable.Creator<Epub> {
                    override fun createFromParcel(parcel: Parcel): Epub {
                        return Epub(parcel)
                    }

                    override fun newArray(size: Int): Array<Epub?> {
                        return arrayOfNulls(size)
                    }
                }
            }

            data class Pdf(
                var acsTokenLink: String?, // http://books.google.co.in/books/download/Young_Children_S_Health_And_Well_Being-sample-pdf.acsm?id=zoHxNzbQa8MC&format=pdf&output=acs4_fulfillment_token&dl_type=sample&source=gbs_api
                var isAvailable: Boolean? // true
            ):Parcelable {
                constructor(parcel: Parcel) : this(
                    parcel.readString(),
                    parcel.readValue(Boolean::class.java.classLoader) as? Boolean
                ) {
                }

                override fun writeToParcel(parcel: Parcel, flags: Int) {
                    parcel.writeString(acsTokenLink)
                    parcel.writeValue(isAvailable)
                }

                override fun describeContents(): Int {
                    return 0
                }

                companion object CREATOR : Parcelable.Creator<Pdf> {
                    override fun createFromParcel(parcel: Parcel): Pdf {
                        return Pdf(parcel)
                    }

                    override fun newArray(size: Int): Array<Pdf?> {
                        return arrayOfNulls(size)
                    }
                }
            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(accessViewStatus)
                parcel.writeString(country)
                parcel.writeValue(embeddable)
                parcel.writeParcelable(epub, flags)
                parcel.writeParcelable(pdf, flags)
                parcel.writeValue(publicDomain)
                parcel.writeValue(quoteSharingAllowed)
                parcel.writeString(textToSpeechPermission)
                parcel.writeString(viewability)
                parcel.writeString(webReaderLink)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<AccessInfo> {
                override fun createFromParcel(parcel: Parcel): AccessInfo {
                    return AccessInfo(parcel)
                }

                override fun newArray(size: Int): Array<AccessInfo?> {
                    return arrayOfNulls(size)
                }
            }
        }

        data class SaleInfo(
            var buyLink: String?, // https://play.google.com/store/books/details?id=XX_-rB07oP0C&rdid=book-XX_-rB07oP0C&rdot=1&source=gbs_api
            var country: String?, // IN
            var isEbook: Boolean?, // false
            var listPrice: ListPrice?,
            var offers: List<Offer?>?,
            var retailPrice: RetailPrice?,
            var saleability: String? // NOT_FOR_SALE
        ) :Parcelable{
            constructor(parcel: Parcel) : this(
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
                parcel.readParcelable(ListPrice::class.java.classLoader),
                parcel.createTypedArrayList(Offer),
                parcel.readParcelable(RetailPrice::class.java.classLoader),
                parcel.readString()
            ) {
            }

            data class ListPrice(
                var amount: Double?, // 1424.55
                var currencyCode: String? // INR
            ):Parcelable {
                constructor(parcel: Parcel) : this(
                    parcel.readValue(Double::class.java.classLoader) as? Double,
                    parcel.readString()
                ) {
                }

                override fun writeToParcel(parcel: Parcel, flags: Int) {
                    parcel.writeValue(amount)
                    parcel.writeString(currencyCode)
                }

                override fun describeContents(): Int {
                    return 0
                }

                companion object CREATOR : Parcelable.Creator<ListPrice> {
                    override fun createFromParcel(parcel: Parcel): ListPrice {
                        return ListPrice(parcel)
                    }

                    override fun newArray(size: Int): Array<ListPrice?> {
                        return arrayOfNulls(size)
                    }
                }
            }

            data class Offer(
                var finskyOfferType: Int?, // 1
                var listPrice: ListPrice?,
                var retailPrice: RetailPrice?
            ):Parcelable {
                constructor(parcel: Parcel) : this(
                    parcel.readValue(Int::class.java.classLoader) as? Int,
                    parcel.readParcelable(ListPrice::class.java.classLoader),
                    parcel.readParcelable(RetailPrice::class.java.classLoader)
                ) {
                }

                data class ListPrice(
                    var amountInMicros: Int?, // 1424550000
                    var currencyCode: String? // INR
                ):Parcelable {
                    constructor(parcel: Parcel) : this(
                        parcel.readValue(Int::class.java.classLoader) as? Int,
                        parcel.readString()
                    ) {
                    }

                    override fun writeToParcel(parcel: Parcel, flags: Int) {
                        parcel.writeValue(amountInMicros)
                        parcel.writeString(currencyCode)
                    }

                    override fun describeContents(): Int {
                        return 0
                    }

                    companion object CREATOR : Parcelable.Creator<ListPrice> {
                        override fun createFromParcel(parcel: Parcel): ListPrice {
                            return ListPrice(parcel)
                        }

                        override fun newArray(size: Int): Array<ListPrice?> {
                            return arrayOfNulls(size)
                        }
                    }
                }

                data class RetailPrice(
                    var amountInMicros: Int?, // 712280000
                    var currencyCode: String? // INR
                ):Parcelable {
                    constructor(parcel: Parcel) : this(
                        parcel.readValue(Int::class.java.classLoader) as? Int,
                        parcel.readString()
                    ) {
                    }

                    override fun writeToParcel(parcel: Parcel, flags: Int) {
                        parcel.writeValue(amountInMicros)
                        parcel.writeString(currencyCode)
                    }

                    override fun describeContents(): Int {
                        return 0
                    }

                    companion object CREATOR : Parcelable.Creator<RetailPrice> {
                        override fun createFromParcel(parcel: Parcel): RetailPrice {
                            return RetailPrice(parcel)
                        }

                        override fun newArray(size: Int): Array<RetailPrice?> {
                            return arrayOfNulls(size)
                        }
                    }
                }

                override fun writeToParcel(parcel: Parcel, flags: Int) {
                    parcel.writeValue(finskyOfferType)
                    parcel.writeParcelable(listPrice, flags)
                    parcel.writeParcelable(retailPrice, flags)
                }

                override fun describeContents(): Int {
                    return 0
                }

                companion object CREATOR : Parcelable.Creator<Offer> {
                    override fun createFromParcel(parcel: Parcel): Offer {
                        return Offer(parcel)
                    }

                    override fun newArray(size: Int): Array<Offer?> {
                        return arrayOfNulls(size)
                    }
                }
            }

            data class RetailPrice(
                var amount: Double?, // 712.28
                var currencyCode: String? // INR
            ):Parcelable {
                constructor(parcel: Parcel) : this(
                    parcel.readValue(Double::class.java.classLoader) as? Double,
                    parcel.readString()
                ) {
                }

                override fun writeToParcel(parcel: Parcel, flags: Int) {
                    parcel.writeValue(amount)
                    parcel.writeString(currencyCode)
                }

                override fun describeContents(): Int {
                    return 0
                }

                companion object CREATOR : Parcelable.Creator<RetailPrice> {
                    override fun createFromParcel(parcel: Parcel): RetailPrice {
                        return RetailPrice(parcel)
                    }

                    override fun newArray(size: Int): Array<RetailPrice?> {
                        return arrayOfNulls(size)
                    }
                }
            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(buyLink)
                parcel.writeString(country)
                parcel.writeValue(isEbook)
                parcel.writeParcelable(listPrice, flags)
                parcel.writeTypedList(offers)
                parcel.writeParcelable(retailPrice, flags)
                parcel.writeString(saleability)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<SaleInfo> {
                override fun createFromParcel(parcel: Parcel): SaleInfo {
                    return SaleInfo(parcel)
                }

                override fun newArray(size: Int): Array<SaleInfo?> {
                    return arrayOfNulls(size)
                }
            }
        }

        data class SearchInfo(
            var textSnippet: String? // The true wealth of a nation can be measured by the <b>health</b> of its youngest citizens<br>, and in the twenty-first century children&#39;s <b>health</b> is still largely determined by <br>social, environmental and economic factors (UNICEF 2001c; Hall and Elliman <br>2003)&nbsp;...
        ):Parcelable {
            constructor(parcel: Parcel) : this(parcel.readString()) {
            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeString(textSnippet)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<SearchInfo> {
                override fun createFromParcel(parcel: Parcel): SearchInfo {
                    return SearchInfo(parcel)
                }

                override fun newArray(size: Int): Array<SearchInfo?> {
                    return arrayOfNulls(size)
                }
            }
        }

        data class VolumeInfo(
            var allowAnonLogging: Boolean?, // false
            var authors: List<String?>?,
            var averageRating: Float?, // 5
            var canonicalVolumeLink: String?, // https://books.google.com/books/about/Young_Children_S_Health_And_Well_Being.html?hl=&id=zoHxNzbQa8MC
            var categories: List<String?>?,
            var contentVersion: String?, // 0.2.1.0.preview.1
            var description: String?, // Looks at the social, environmental, and economic influences at work today in determining the health and well-being of the world's children.
            var imageLinks: ImageLinks?,
            var industryIdentifiers: List<IndustryIdentifier?>?,
            var infoLink: String?, // http://books.google.co.in/books?id=zoHxNzbQa8MC&dq=health&hl=&source=gbs_api
            var language: String?, // en
            var maturityRating: String?, // NOT_MATURE
            var pageCount: Int?, // 205
            var panelizationSummary: PanelizationSummary?,
            var previewLink: String?, // http://books.google.co.in/books?id=zoHxNzbQa8MC&pg=PA1&dq=health&hl=&cd=2&source=gbs_api
            var printType: String?, // BOOK
            var publishedDate: String?, // 2006-12-01
            var publisher: String?, // McGraw-Hill Education (UK)
            var ratingsCount: Int?, // 1
            var readingModes: ReadingModes?,
            var subtitle: String?, // Relevance for Professionals and Issues for the Future
            var title: String? // Young Children'S Health And Well-Being
        ) :Parcelable{
            constructor(parcel: Parcel) : this(
                parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
                parcel.createStringArrayList(),
                parcel.readValue(Int::class.java.classLoader) as? Float,
                parcel.readString(),
                parcel.createStringArrayList(),
                parcel.readString(),
                parcel.readString(),
                parcel.readParcelable(ImageLinks::class.java.classLoader),
                parcel.createTypedArrayList(IndustryIdentifier),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readParcelable(PanelizationSummary::class.java.classLoader),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readString(),
                parcel.readValue(Int::class.java.classLoader) as? Int,
                parcel.readParcelable(ReadingModes::class.java.classLoader),
                parcel.readString(),
                parcel.readString()
            ) {
            }

            data class ImageLinks(
                var smallThumbnail: String?, // http://books.google.com/books/content?id=zoHxNzbQa8MC&printsec=frontcover&img=1&zoom=5&edge=curl&source=gbs_api
                var thumbnail: String? // http://books.google.com/books/content?id=zoHxNzbQa8MC&printsec=frontcover&img=1&zoom=1&edge=curl&source=gbs_api
            ):Parcelable {
                constructor(parcel: Parcel) : this(
                    parcel.readString(),
                    parcel.readString()
                ) {
                }

                override fun writeToParcel(parcel: Parcel, flags: Int) {
                    parcel.writeString(smallThumbnail)
                    parcel.writeString(thumbnail)
                }

                override fun describeContents(): Int {
                    return 0
                }

                companion object CREATOR : Parcelable.Creator<ImageLinks> {
                    override fun createFromParcel(parcel: Parcel): ImageLinks {
                        return ImageLinks(parcel)
                    }

                    override fun newArray(size: Int): Array<ImageLinks?> {
                        return arrayOfNulls(size)
                    }
                }
            }

            data class IndustryIdentifier(
                var identifier: String?, // 9780335219063
                var type: String? // ISBN_13
            ):Parcelable {
                constructor(parcel: Parcel) : this(
                    parcel.readString(),
                    parcel.readString()
                ) {
                }

                override fun writeToParcel(parcel: Parcel, flags: Int) {
                    parcel.writeString(identifier)
                    parcel.writeString(type)
                }

                override fun describeContents(): Int {
                    return 0
                }

                companion object CREATOR : Parcelable.Creator<IndustryIdentifier> {
                    override fun createFromParcel(parcel: Parcel): IndustryIdentifier {
                        return IndustryIdentifier(parcel)
                    }

                    override fun newArray(size: Int): Array<IndustryIdentifier?> {
                        return arrayOfNulls(size)
                    }
                }
            }

            data class PanelizationSummary(
                var containsEpubBubbles: Boolean?, // false
                var containsImageBubbles: Boolean? // false
            ):Parcelable {
                constructor(parcel: Parcel) : this(
                    parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
                    parcel.readValue(Boolean::class.java.classLoader) as? Boolean
                ) {
                }

                override fun writeToParcel(parcel: Parcel, flags: Int) {
                    parcel.writeValue(containsEpubBubbles)
                    parcel.writeValue(containsImageBubbles)
                }

                override fun describeContents(): Int {
                    return 0
                }

                companion object CREATOR : Parcelable.Creator<PanelizationSummary> {
                    override fun createFromParcel(parcel: Parcel): PanelizationSummary {
                        return PanelizationSummary(parcel)
                    }

                    override fun newArray(size: Int): Array<PanelizationSummary?> {
                        return arrayOfNulls(size)
                    }
                }
            }

            data class ReadingModes(
                var image: Boolean?, // true
                var text: Boolean? // false
            ):Parcelable {
                constructor(parcel: Parcel) : this(
                    parcel.readValue(Boolean::class.java.classLoader) as? Boolean,
                    parcel.readValue(Boolean::class.java.classLoader) as? Boolean
                ) {
                }

                override fun writeToParcel(parcel: Parcel, flags: Int) {
                    parcel.writeValue(image)
                    parcel.writeValue(text)
                }

                override fun describeContents(): Int {
                    return 0
                }

                companion object CREATOR : Parcelable.Creator<ReadingModes> {
                    override fun createFromParcel(parcel: Parcel): ReadingModes {
                        return ReadingModes(parcel)
                    }

                    override fun newArray(size: Int): Array<ReadingModes?> {
                        return arrayOfNulls(size)
                    }
                }
            }

            override fun writeToParcel(parcel: Parcel, flags: Int) {
                parcel.writeValue(allowAnonLogging)
                parcel.writeStringList(authors)
                parcel.writeValue(averageRating)
                parcel.writeString(canonicalVolumeLink)
                parcel.writeStringList(categories)
                parcel.writeString(contentVersion)
                parcel.writeString(description)
                parcel.writeParcelable(imageLinks, flags)
                parcel.writeTypedList(industryIdentifiers)
                parcel.writeString(infoLink)
                parcel.writeString(language)
                parcel.writeString(maturityRating)
                parcel.writeValue(pageCount)
                parcel.writeParcelable(panelizationSummary, flags)
                parcel.writeString(previewLink)
                parcel.writeString(printType)
                parcel.writeString(publishedDate)
                parcel.writeString(publisher)
                parcel.writeValue(ratingsCount)
                parcel.writeParcelable(readingModes, flags)
                parcel.writeString(subtitle)
                parcel.writeString(title)
            }

            override fun describeContents(): Int {
                return 0
            }

            companion object CREATOR : Parcelable.Creator<VolumeInfo> {
                override fun createFromParcel(parcel: Parcel): VolumeInfo {
                    return VolumeInfo(parcel)
                }

                override fun newArray(size: Int): Array<VolumeInfo?> {
                    return arrayOfNulls(size)
                }
            }
        }

        override fun writeToParcel(parcel: Parcel, flags: Int) {
            parcel.writeParcelable(accessInfo, flags)
            parcel.writeString(etag)
            parcel.writeString(id)
            parcel.writeString(kind)
            parcel.writeParcelable(saleInfo, flags)
            parcel.writeParcelable(searchInfo, flags)
            parcel.writeString(selfLink)
            parcel.writeParcelable(volumeInfo, flags)
        }

        override fun describeContents(): Int {
            return 0
        }

        companion object CREATOR : Parcelable.Creator<Item> {
            override fun createFromParcel(parcel: Parcel): Item {
                return Item(parcel)
            }

            override fun newArray(size: Int): Array<Item?> {
                return arrayOfNulls(size)
            }
        }
    }
}