package day05

class CmpInt(a: Int, b: Int) {
    def bigger = if(a > b) a else b
}

class CmpLong(a: Long, b: Long) {
    def bigger = if(a > b) a else b
}

/**
  * <: 上界 upper bounds
  * 类似java中的 <T extends Comparable>
  *     不会发生隐式转换，除非用户显示的指定
  *     T 实现了 Comparable 接口
  */
//class CmpComm[T <: Comparable[T]](o1: T, o2: T) {
//    def bigger = if(o1.compareTo(o2) > 0) o1 else o2
//}

/**
  * <% 视图界定 view bounds
  *   会发生隐式转换
//  */
//class CmpComm[T <% Comparable[T]](o1: T, o2: T) {
//    def bigger = if(o1.compareTo(o2) > 0)  o1 else o2
//}

// 第二个版本
//class CmpComm[T <% Ordered[T]](o1: T, o2: T) {
//    def bigger = if(o1 > o2)  o1 else o2
//}

// Comparator 传递比较器
/**
  * 上下文界定
  *     也会隐式转换
  */
//class CmpComm[T: Ordering](o1: T, o2: T)(implicit cmptor: Ordering[T]) {
//    def bigger = if (cmptor.compare(o1, o2) > 0) o1 else o2
//}

//class CmpComm[T: Ordering](o1: T, o2: T) {
//    def bigger = {
//        def inner(implicit cmptor: Ordering[T]) = cmptor.compare(o1, o2)
//        if (inner > 0) o1 else o2
//    }
//}

class CmpComm[T: Ordering](o1: T, o2: T) {
    def bigger = {
        val cmptor = implicitly[Ordering[T]]
        if(cmptor.compare(o1, o2) > 0) o1 else o2
    }
}


//class Students(val name: String, val age: Int) extends Ordered[Students]{
//    override def compare(that: Students): Int = this.age - that.age
//
//    override def toString: String = this.name + "\t" + this.age
//}

/**
  * 和MyImpicits 中的隐式转换以及视图界定/上下文界定一起 隐式将Student -> Ordered[Student]
  * 可用来比较排序
  * 视图界定T <% Comparable[T] 和MyImpicits 中的隐式转换new Ordered[Students]对应
  * 上下文界定T: Ordering 和 MyImpicits 中的隐式转换new Ordering[Students] new Ordered[Students]都可以匹配
  * @param name
  * @param age
  */
class Students(val name: String, val age: Int) {
    override def toString: String = this.name + "\t" + this.age
}





object ScalaUpperLowerBounds {

    // 隐式将Student -> Ordered[Student]
//    implicit def student2OrderedStu(stu: Students) = new Ordered[Students]{
//        println("Ordered")
//        override def compare(that: Students): Int = stu.age - that.age
//    }
//
//    // 一个隐式对象实例 -> 一个又具体实现的Comparator
//
    implicit val comparatorStu = new Ordering[Students] {
        println("Ordering")
        override def compare(x: Students, y: Students): Int = x.age - y.age
    }


    def main(args: Array[String]): Unit = {

//        val cmpInt = new CmpLong(8L, 9L)
//        println(cmpInt.bigger)

//         val cmpcom = new CmpComm(1, 2) // 上界的时候会报错
//        val cmpcom = new CmpComm(Integer.valueOf(1), Integer.valueOf(2))
//        val cmpcom = new CmpComm[Integer](1, 2)
//        println(cmpcom.bigger)

//
//        import MyImpicits._

        val tom = new Students("Tom", 18)
        val jim = new Students("Jim", 20)
        val cmpcom = new CmpComm(tom, jim)

        println(cmpcom.bigger)


    }

}
