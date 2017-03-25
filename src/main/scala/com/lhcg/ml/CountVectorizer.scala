package com.lhcg.ml

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext
import org.apache.spark.ml.feature.{CountVectorizer, CountVectorizerModel}

/**
  * Created by lhcg on 14/3/2017.
  */
object CountVectorizer {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("CountVectorizer")
    //      .setMaster("local[2]")
    val spark = new SparkContext(conf)
    val sqlContext = new SQLContext(spark)

    val df = sqlContext.createDataFrame(Seq(
      (0, Array("a", "b", "c")),
      (1, Array("a", "b", "b", "c", "a"))
    )).toDF("id", "words")

    // fit a CountVectorizerModel from the corpus
    val cvModel: CountVectorizerModel = new CountVectorizer()
      .setInputCol("words")
      .setOutputCol("features")
      .setVocabSize(3)
      .setMinDF(2)
      .fit(df)

    // alternatively, define CountVectorizerModel with a-priori vocabulary
    val cvm = new CountVectorizerModel(Array("a", "b", "c"))
      .setInputCol("words")
      .setOutputCol("features")

    cvModel.transform(df).select("features").show()
  }
}
