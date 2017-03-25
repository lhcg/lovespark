package com.lhcg.ml

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.feature.SQLTransformer
import org.apache.spark.sql.SQLContext
/**
  * Created by lhcg on 14/3/2017.
  */
object SQLTransformer {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("SQLTransformer")
    //      .setMaster("local[2]")
    val spark = new SparkContext(conf)
    val sqlContext = new SQLContext(spark)

    val df = sqlContext.createDataFrame(
      Seq((0, 1.0, 3.0), (2, 2.0, 5.0))).toDF("id", "v1", "v2")

    val sqlTrans = new SQLTransformer().setStatement(
      "SELECT *, (v1 + v2) AS v3, (v1 * v2) AS v4 FROM __THIS__")

    sqlTrans.transform(df).show()
  }
}
