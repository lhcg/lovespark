package com.lhcg.ml
import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.clustering.{KMeans, KMeansModel}

/**
  * kmeans算法对数据进行分类
  * Created by lhcg on 14/3/2017.
  */
object Kmeans {
  def main(args: Array[String]): Unit = {
    //设置环境
    val conf = new SparkConf().setAppName("Kmeans")
          .setMaster("local[2]")
    val sc = new SparkContext(conf)

    // Load and parse the data 装载数据
    val data = sc.textFile("/Users/lhcg/data/workspace/lovespark/src/main/resources/data/mllib/kmeans_data.txt")
    val parsedData = data.map(s => Vectors.dense(s.split(' ').map(_.toDouble))).cache()

    // Cluster the data into two classes using KMeans 模型训练
    val numClusters = 4
    val numIterations = 20
    val clusters = KMeans.train(parsedData, numClusters, numIterations)

    println("Cluster centers:")
    for (c <- clusters.clusterCenters) {
      println("  " + c.toString)
    }

    //使用模型测试单点数据
    //运行结果
    //    Vectors 0.2 0.2 0.2 is belongs to clusters:0
    //    Vectors 0.25 0.25 0.25 is belongs to clusters:0
    //    Vectors 8 8 8 is belongs to clusters:1
    println("Vectors 0.2 0.2 0.2 is belongs to clusters:" + clusters.predict(Vectors.dense("0.2 0.2 0.2".split(' ').map(_.toDouble))))
    println("Vectors 0.25 0.25 0.25 is belongs to clusters:" + clusters.predict(Vectors.dense("0.25 0.25 0.25".split(' ').map(_.toDouble))))
    println("Vectors 8 8 8 is belongs to clusters:" + clusters.predict(Vectors.dense("8 8 8".split(' ').map(_.toDouble))))
    println("Vectors 9.0 9.0 9.0 is belongs to clusters:" + clusters.predict(Vectors.dense("9.0 9.0 9.0".split(' ').map(_.toDouble))))
    //交叉评估1，只返回结果
    val testdata = data.map(s =>Vectors.dense(s.split(' ').map(_.toDouble)))
    val result1 = clusters.predict(testdata)
    for (c <- result1) {
      println("  " + c.toString)
    }

    // 交叉评估2，返回数据集和结果
    val result2 = data.map {
      line =>
        val linevectore = Vectors.dense(line.split(' ').map(_.toDouble))
        val prediction = clusters.predict(linevectore)
        line + " " + prediction

    }
    for (c <- result2){
      println(c)
    }
    // Evaluate clustering by computing Within Set Sum of Squared Errors
    // 使用误差平方之和来评估数据模型
    val WSSSE = clusters.computeCost(parsedData)
    println("Within Set Sum of Squared Errors = " + WSSSE)
//    println(clusters.toPMML())

    // Save and load model
    clusters.save(sc, "target/org/apache/spark/KMeansExample/KMeansModel")
//    val sameModel = KMeansModel.load(sc, "target/org/apache/spark/KMeansExample/KMeansModel")
//    println(sameModel.toString)
  }
}
