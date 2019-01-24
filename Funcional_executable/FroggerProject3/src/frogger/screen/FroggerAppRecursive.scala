package frogger.screen

import javafx.scene.shape.Rectangle
import javafx.scene.Node
import javafx.scene.input.KeyCode
import javafx.scene.paint.{Color, Paint}

import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global
import collection.mutable._
import collection.JavaConverters._
import collection.{JavaConverters, mutable}
import scala.util.{Failure, Success}
object functions {
  //Lembrar algumas funções são usadas junto com Map e Reduce
  //Retangle Creation Functions
  var RetangleCar: (Double, Double) => Rectangle = (x: Double, y: Double) => new Rectangle(x, y, 40, 40)
  var RetangleLog = (x: Double, y: Double) => new Rectangle(x, y, 90, 300)
  var RetangleFrog: (Double, Double) => Rectangle = (x: Double, y: Double) => new Rectangle(x, y, 38, 38)
  var RetangleLake = (x: Double, y: Double) => new Rectangle(x, y, 800, 335)
  //Move
  var MoveNode = (position: java.lang.Double, move: java.lang.Double) => position + move;
  //Frog Death Aux
  var DeathCar = (frog: Node) => (x: Boolean, y: Boolean) => x || y
  var DeathLogs = (frog: Node) => (log1: Boolean, log2: Boolean) => log1 || log2
  var MapContact = (frog: Node) => (item: Rectangle) => item.getBoundsInParent.intersects(frog.getBoundsInParent)
  var CheckLake = (frog: Node, lake: Rectangle) => lake.getBoundsInParent.intersects(frog.getBoundsInParent)
////=============================================================================================================================
  //Cars


  //Car Main Functions
  //Acha a função para mover o carro certo lidando com uma lista de Funções e retangulos ,usando o uso de recursão como forma de iteração ,ao mesmo tempo sendo uma função de alta ordem que tem função como parametro e retorno
  var SpawnCars:(List[Rectangle],List[Double=>Unit])=>(Double=>Unit)
  = (cars: List[Rectangle],ChangeFunction:List[Double=>Unit])
  => cars match {
      case Nil => (x: Double) =>
      case _ => cars.head.getTranslateX match {
        case 10000=> ChangeFunction.head
        case x1 if x1>800||x1<0 => ChangeFunction.head
        case _ => cars.isEmpty || ChangeFunction.isEmpty match {
          case true => (x: Double) =>
          case false => SpawnCars(cars.tail, ChangeFunction.tail)
        }
      }
    }
  var SetTraslateY:(Node)=>(Double=>Unit)= (node:Node)=>
    node.setTranslateY
  var SetTraslateX:(Node)=>(Double=>Unit)= (node:Node)=>
    node.setTranslateX


  def SpawnCarY(random:java.lang.Double,cars:java.util.List[Rectangle],SpawnRate:java.lang.Double,Right:Boolean,MinSpawnY:Double,MaxSpawnY:Double,randomP:Double): Unit ={
    random  match {
      case r if random < SpawnRate => SpawnCars(cars.asScala.toList,cars.asScala.toList.map(SetTraslateY))(GenYSpawn(randomP,Right))
      case _ =>
    }
  }
  var GenYSpawn =(randomP:Double,Right:Boolean)=> Right match{
    case true=> ( 0.1 * (2 + (7-1)) * 14 * 40)
    case false => randomP match{
      case r if r>0.5=> ( 0.1 * (1 + (7-1)) * 14 * 40)
      case _ =>  ( 0.1 * (3 + (7-1)) * 14 * 40)
    }
  }
  def SpawnCarX(random:java.lang.Double,cars:java.util.List[Rectangle],SpawnRate:java.lang.Double,Right:Boolean): Unit ={
    random  match {
      case r if random < SpawnRate => SpawnCars(cars.asScala.toList,cars.asScala.toList.map(SetTraslateX))(Position(Right))
      case _ =>
    }
  }
  var Position= (Right:Boolean) => Right match{
    case true => 0
    case false =>800
  }
  var getX:java.util.List[Rectangle]=>List[Double]= (nodes: java.util.List[Rectangle])=> nodes.asScala.toList.map((node:Rectangle)=>node.getTranslateX)
  var mapspawn = (move:Double)=>(x:Double)=> x match{
          case 100000 => 100000
          case x1 if x1>800||x1<0 => 100000
          case _ => x + move
        }
  def Movecars(nodes: java.util.List[Rectangle],move:java.lang.Double):java.util.List[java.lang.Double] = {
    getX(nodes).map((x:Double)=> x match{
      case 100000 => 100000
      case x1 if x1>800||x1<0 => 100000
      case _ => x + move
    }).map(Double.box).asJava
  }

  def LogMain(log:Rectangle,Right:Boolean,Move:Double): Unit ={
    SetTraslateX(log)(LogRestart(Right,log.getTranslateX,Move))
  }
  def LogRestart(Right:Boolean,Position:Double,move:Double)={
    Right match{
      case true=> Position-300 match{
        case x if x>800 => -300.0
        case _ => Position+move
      }
      case false=> Position+300 match{
        case x if x<0 =>  800.0
        case _ => Position+move
      }
    }
  }
  def Win(frog:Node,Goal:Rectangle): Unit ={
    MapContact(frog)(Goal) match{
      case true => java.lang.System.exit(0)
      case false =>
    }
  }

  ////=============================================================================================================================

 //=================================================================================== //Log

  /*var LogMain = (logs: java.util.List[Rectangle], Right: Boolean, Limit: Double, res: Double, move: Double) => Right match {
    case true => logs.
    case false => logs.
  }
  var MovingObjAction = (Right: Boolean, restartFunction: Double => Boolean, RestPos: Double, move: Double) => (log: Rectangle) => MoveNode(log.getTranslateX, move) match {
    case x1 if restartFunction(x1) => RetangleLog(RestPos, log.getTranslateY)
    case _ => RetangleLog(log.getTranslateX + move, log.getLayoutY)
  }*/
//==================================================================================================================================//
  //Move frog

  var MoveNodeFrog = (position: java.lang.Double, move: java.lang.Double, LimitBorderFunction: (Double,Double) => Double) =>
    LimitBorderFunction(position+move,move)
  //Frog Limit Functions
  var FrogLimitX = (x: Double,move:Double) => x match {
    case x1 if x1 >= 800 || x < 0 => x-move
    case _ => x
  }
  var FrogLimitY = (y: Double,move:Double) => y match {
    case y1 if y1 >= 600 => y - move
    case _ => y
  }
  //Frog Functions
  var moveFrogY=(key: KeyCode, node: Node,NodeTrasfomation:Double=>Unit) =>
    key match {
      case KeyCode.W => NodeTrasfomation(MoveNodeFrog(node.getTranslateY, -40.0, FrogLimitY))
      case KeyCode.S => NodeTrasfomation(MoveNodeFrog(node.getTranslateY, 40.0, FrogLimitY))
      case _ => NodeTrasfomation(node.getTranslateY)
    }
  //função de alta ordem
  var moveFrogX = (key: KeyCode, node: Node,NodeTrasformation:Double=>Unit) =>
    key match {
      case KeyCode.A => NodeTrasformation(MoveNodeFrog(node.getTranslateX(), -40.0, FrogLimitX))
      case KeyCode.D => NodeTrasformation(MoveNodeFrog(node.getTranslateX(), 40.0, FrogLimitX))
      case _ => RetangleFrog(node.getTranslateX,node.getTranslateY)
    }

    def MainFrogMov(frog: Node, key: KeyCode): Unit = {
      key match {
        case KeyCode.W => moveFrogY(key, frog, frog.setTranslateY)
        case KeyCode.S => moveFrogY(key, frog, frog.setTranslateY)
        case KeyCode.A => moveFrogX(key, frog, frog.setTranslateX)
        case  KeyCode.D => moveFrogX(key, frog, frog.setTranslateX)
      }
    }
  def frogDeath(frog: Node, Lake: Rectangle, LogR:Rectangle,LogL:Rectangle,LogLL:Rectangle, carsR: java.util.List[Rectangle], carsL: java.util.List[Rectangle], restart: java.lang.Double, atual: java.lang.Double)={
    var carL =carsSIDEContact(frog,carsR.asScala.toList,Lake)
    var carR =carsSIDEContactR(frog,carsL.asScala.toList,Lake,carL)
    FrogDeath(frog, Lake, List(LogR,LogL,LogLL), carsR.asScala.toList,carsL.asScala.toList, restart, atual,Await.result(carR,100 seconds))
  }
  val p = Promise[Boolean]()
  val f = p.future

  val carsSIDEContact=(frog:Node,cars: List[Rectangle],Lake:Rectangle)=>{
    Future[Boolean] {
      CheckLake(frog, Lake) match {
        case true => true
        case false => cars.map((item: Rectangle) => item.getBoundsInParent.intersects(frog.getBoundsInParent)).reduce(_ || _)
      }
    }
  }
  var carsSIDEContactR=(frog:Node,cars: List[Rectangle],Lake:Rectangle,future2:Future[Boolean])=>{
    Future[Boolean] {
      CheckLake (frog, Lake) match {
        case true => true
        case false => cars.map ((item: Rectangle) => item.getBoundsInParent.intersects(frog.getBoundsInParent) ).reduce (_ || _ ) match{
          case true=>true
          case false=>Await.result(future2,100 seconds)
        }
      }
    }
  }

  var FrogDeath:( Node,Rectangle,List[Rectangle],List[Rectangle],List[Rectangle],Double,Double,Boolean)=> java.lang.Double= (frog: Node, Lake: Rectangle, Logs: List[Rectangle], carsR: List[Rectangle], carsL: List[Rectangle], restart: Double, atual: Double,carContact:Boolean) =>
    frog.getTranslateY  match {
      case y if y < 161 => y
      case _ => CheckLake(frog, Lake) match {
        case true => !(Logs.map((item: Rectangle) => item.getBoundsInParent.intersects(frog.getBoundsInParent)).reduce( _ || _ )) match {
          case true => restart
          case false => atual
        }
        case false=> carContact match {
          case true => restart
          case false =>carContact match{
            case true=>restart
            case false =>atual
          }
        }
      }
    }
  def Recursive(List1:List[Rectangle],List3:List[java.lang.Double],Set:Node=>Double=>Unit): Unit ={
    List1 match {
      case Nil=>
      case _ =>
    Set (List1.head) (List3.head)
    Recursive (List1.tail, List3.tail, Set)
    }
  }
  def Recursivemain(List1:java.util.List[Rectangle],ListX:java.util.List[java.lang.Double]): Unit ={
    Recursive(List1.asScala.toList,ListX.asScala.toList,SetTraslateX)
  }
}
