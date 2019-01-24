package frogger.screen.gamescene
import java.util
import java.util.List

import scala.collection.parallel
import frogger.screen.`object`.Car.Car
import frogger.screen.`object`.Frog.{Frog, PositionObject, RetangleObject}
import frogger.screen.`object`.Log.Lake
import frogger.screen.`object`.Log.Log
import javafx.scene.image.Image
import javafx.scene.layout._
import javafx.scene.paint.Color
import javafx.scene.text.Text

import scala.collection.parallel.immutable.ParRange
class gamescene( var root: Pane ,var lake: Lake ){
   var idscene:IDOBJETO[Int]= new IDOBJETO[Int]
  idscene.id_=(1)
  private var _cars:scala.List[Car]=scala.List()
  def cars = _cars
  def cars_= (newVal:scala.List[Car]) = _cars = newVal
  var frog:Frog=new Frog()
  def addObject(positionOb: Car): Unit ={
    root.getChildren().add(positionOb.getNode())
    cars=cars:::scala.List(positionOb);
  }
  def addObject(positionObject:Frog): Unit ={
    root.getChildren().add(positionObject.getNode())
    frog=positionObject;
  }
  def SpawnCars(): Unit ={
    import scala.collection.JavaConversions._
    for (car <- cars) {
      car.move()
    }
    if (Math.random < 0.010) {
      val carnew = new Car(40.toDouble, 40.toDouble, Color.RED, 7.toDouble, 9.toDouble)
      this.addObject(carnew)
    }
  }
  def FrogCheckState(): Unit ={
    import scala.collection.JavaConversions._
    for (car <- cars) {
      if (car.intersect(frog.getNode)) frog.move(0, 600 - 39)
    }
  }

  private var _win:Boolean = false


  for(log<-lake.logs){
    root.getChildren().add(log.getNode())
  }
  def addObject(positionOb: RetangleObject): Unit ={
       root.getChildren().add(positionOb.getNode())
  }

  def addHbox(hBox: HBox ): Unit ={
    root.getChildren().add(hBox);
  }
  def getRoot(): Pane ={
    return root
  }
  def win:Boolean = _win
  def win_= (value:Boolean):Unit = _win= value
  def MoveFrogLake(frog : Frog,x: java.lang.Double,y: java.lang.Double): Unit ={
    if(y<161){
      win=true
    }
    if(frog.NodeY()<361&&frog.NodeY()>=161||frog.NodeY()==361&&y<=frog.NodeY()) {
      for (log <- lake.logs) {
        frog.move(x, y)
        if (frog.intersect(log.getNode())) {
          print(frog.NodeY())
          return
        }
      }
      frog.move(0.toDouble, 600.toDouble - 39);
    }
    else {
      frog.move(x, y)
    }
    }
  def movelogs(velo:java.lang.Double): Unit ={
    var i:Double=0;
    for(log <- lake.logs){
      i+=1
      log.move(i)
    }
  }
  def +(that: RetangleObject): Unit ={
    root.getChildren().add(that.getNode())
  }

}
