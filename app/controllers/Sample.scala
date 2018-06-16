package controllers

/**
 * Created by rohanp on 6/9/18.
 */

object Sample extends App {
  def mklsts( arr : Array[Int] ) : Int = {
    val countList : List[Int] = Nil
    var count = 0
    for(i <- 0 to arr.sorted.length){
      if( arr(i) == arr(i+1)+1 ){
        count += 1
        countList :+ count
      }
    }
    println(s"====>$countList")
    countList.max
  }


  //def main(args : Array[String]) = {
    val arr = Array[Int](500,501,1,2,3,4,150,151,152,1,2,3)
    //val obj = new Sample
    mklsts(arr)
  //}
}