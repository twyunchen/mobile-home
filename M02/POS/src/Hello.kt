import java.lang.StringBuilder
import BuyTwoGetOneFreePromotion as BuyTwoGetOneFreePromotion

fun main() {
    val receipt = getReceipt()
    println(receipt)
    val result = when (receipt == expectedReceipt) {
        true -> "正确 ✅"
        false -> "错误 ❌"
    }
    println("\n结果：${result}")
}

interface Promotion {
    var barcodes: List<String>
    fun getDiscount(barcode: String, price: Double, count: Int): Double
}

class BuyTwoGetOneFreePromotion(override var barcodes: List<String>) : Promotion {
    override fun getDiscount(barcode: String, price: Double, count: Int): Double {
        if (barcodes.contains(barcode)) {
            return count / 3 * price;
        }
        return 0.00;
    }
}

fun loadPromotions(): List<Promotion> = listOf(BuyTwoGetOneFreePromotion(listOf("ITEM000000", "ITEM000001", "ITEM000005")))

data class Item(val barcode: String, val name: String, val unit: String, val price: Double) {
    override fun equals(other: Any?): Boolean {
        return when (other) {
            !is Item -> false
            else -> this === other || barcode == other.barcode;
        }

    }
}

fun loadAllItems(): List<Item> {
    return listOf(
            Item("ITEM000000", "可口可乐", "瓶", 3.00),
            Item("ITEM000001", "雪碧", "瓶", 3.00),
            Item("ITEM000002", "苹果", "斤", 5.50),
            Item("ITEM000003", "荔枝", "斤", 15.00),
            Item("ITEM000004", "电池", "个", 2.00),
            Item("ITEM000005", "方便面", "袋", 4.50)
    )
}

val purchasedBarcodes = listOf(
        "ITEM000001",
        "ITEM000001",
        "ITEM000001",
        "ITEM000001",
        "ITEM000001",
        "ITEM000003-2",
        "ITEM000005",
        "ITEM000005",
        "ITEM000005"
)


fun getOrderItems(purchasedBarcodes: List<String>): List<OrderItem> {
    var orderItems = mutableListOf<OrderItem>()
    for (purchasedBarCode in purchasedBarcodes) {
        var itemBarCode = purchasedBarCode;
        var count = 1;

        if (purchasedBarCode.contains("-")) {
            var splitBarCodes = purchasedBarCode.split("-");
            itemBarCode = splitBarCodes[0];
            count = splitBarCodes[1].toInt();
        }

        var item = getItemByBarcode(itemBarCode);

        var orderItem = orderItems.firstOrNull { x -> x.item.equals(item) }

        when (orderItem) {
            null -> orderItems.add(OrderItem(item, count))
            else -> orderItem.count += count
        }
    }
    return orderItems
}

fun getItemByBarcode(barcode: String): Item {
    var items = loadAllItems();
    var item = items.first { x -> x.barcode == barcode }
    return item;
}

data class OrderItem(var item: Item, var count: Int) {


    fun calculatePrice(): Pair<Double, Double> {
        val promotionList = loadPromotions()
        var discount = 0.00;
        if (promotionList?.size > 0) {
            for (promotion in promotionList) {
                discount += promotion.getDiscount(item.barcode, item.price, count)
            }
        }

        val totalPrice = item.price * count - discount;

        return totalPrice to discount;
    }
}

const val HEADER = "***<没钱赚商店>收据***";
const val SPLITER = "----------------------";
const val FOOTER = "**********************";

fun getReceipt(): String {
    val sb = StringBuilder()
    sb.appendln()
    sb.appendln(HEADER)

    var totalPrice = 0.00;
    var totalDiscount = 0.00;

    var orderItems = getOrderItems(purchasedBarcodes);

    for (orderItem in orderItems) {

        var item = orderItem.item
        var itemPriceInfo = orderItem.calculatePrice();
        totalPrice += itemPriceInfo.first;
        totalDiscount += itemPriceInfo.second;
        val itemReceipt = """名称：${item.name}，数量：${orderItem.count}${item.unit}，单价：${item.price.format()}(元)，小计：${itemPriceInfo.first}(元)"""

        sb.appendln(itemReceipt);
    }
    sb.appendln(SPLITER);
    sb.appendln("""总计：${totalPrice.format()}(元)""");
    sb.appendln("""节省：${totalDiscount.format()}(元)""");
    sb.appendln(FOOTER);

    return sb.toString()
}

fun Double.format() = "%.2f".format(this)

const val expectedReceipt = """
***<没钱赚商店>收据***
名称：雪碧，数量：5瓶，单价：3.00(元)，小计：12.0(元)
名称：荔枝，数量：2斤，单价：15.00(元)，小计：30.0(元)
名称：方便面，数量：3袋，单价：4.50(元)，小计：9.0(元)
----------------------
总计：51.00(元)
节省：7.50(元)
**********************
"""
