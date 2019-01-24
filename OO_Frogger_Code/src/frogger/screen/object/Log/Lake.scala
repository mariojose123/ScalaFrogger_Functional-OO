package frogger.screen.`object`.Log

import java.lang

import frogger.screen.`object`.Frog.RetangleObject
import frogger.screen.gamescene.gamescene
import javafx.scene.layout.Pane
import javafx.scene.paint.Color


class Lake ( var height:java.lang.Double ,private var width: java.lang.Double ,private var color: Color ,private var  Nlogs : java.lang.Double , private var LogStart : java.lang.Double,Logcolor: Color,logWidth:java.lang.Double,logheight:java.lang.Double) extends RetangleObject {

  var logs: scala.List[Log]=scala.List();
  def +(that: Log) {
    logs=logs:::List(that)
  }
  def initNormalLake(): Unit ={
    var newlog4: Log = new Log(logheight, logWidth, Logcolor,5.0, false)
    var newlog1: Log = new Log(logheight, logWidth, Logcolor,4.0, true)
    var newlog2: Log = new Log(logheight, logWidth, Logcolor,3.0, false)
    this+newlog1
    this+newlog2
    this+newlog4
    print(this.logs.toString())
  }

  def moveLog(): Unit = {
    var i:Double=0.0;
    for (log <- logs) {
      i+=2;
      print(i)
      log.move(i)
    }
  }
  def Spawn(): Unit ={
    for(log<-logs){
        if (log.right) {
          if((log).NodeX()+300<0) {
            log.move(800, 0.1 * log.positionY * 14 * 40)
          }
        }
        else {
          if(log.NodeX()-300>800) {
            log.move(-300, 0.1 * log.positionY * 14 * 40)
          }
        }
      }
    }

}
