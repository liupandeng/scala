package day02

object ScalaPartialFunction {


    def func(str: String) : Int = {
        if(str.equals("a")) 97
        else 0
    }

    /**
      * 偏函数：PartialFunction[参数类型，返回值类型]
      */
    def func1: PartialFunction[String, Int] = {
        case "a" => 97
        case _ => 0
    }


    def f1: PartialFunction[Any, Int] = {
        case i: Int => i * 10
    }


    def main(args: Array[String]): Unit = {
        println(func("a"))
        println(func1("a"))


        val arr = Array[Any](1,2,4, "你大爷的")
        val arr1 = Array[Int](1,2,4)

        val collect = arr.collect(f1)


        val array = arr1.map {case x: Int => x*10}
        val ints1 = arr1.map((x: Int) => x*10)
        val ints = arr1.map (_*10)
       /* for(i<- array){
            println(i)
        }*/

        println(collect.toBuffer)
        println(array.toBuffer)



    }
}
