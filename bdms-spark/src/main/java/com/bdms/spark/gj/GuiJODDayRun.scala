package com.bdms.spark.gj

object GuiJODDayRun extends Serializable {

  def main(args: Array[String]) {
    
    val day = new GuiJODDay()
    day.startApp("GuiJODDay", "hdfs://dswhhadoop-1:8020/dams/ODData/"+args(0))
  }
}