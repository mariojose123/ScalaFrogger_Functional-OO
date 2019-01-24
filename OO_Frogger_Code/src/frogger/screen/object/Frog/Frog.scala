package frogger.screen.`object`.Frog
import javafx.scene.Node;
import scala.collection.mutable
import javafx.scene.paint.Color

class Frog(var height:java.lang.Double ,var width: java.lang.Double ,var color: Color ) extends RetangleObject {
  try
  {
    setGameObjectRectangle(height,width, color)
  }
  catch {
    case e:ExceptionInInitializerError => print("ERRO DE INICIALIZAÇÃO")
  }
  def this(height:java.lang.Double ,width:java.lang.Double )=this(height,width,Color.GREEN);
  def this()=this(38.0,38.0,Color.GREEN);
  def CheckWin(): Boolean ={
     return (NodeY()<=0);
  }

  override def move(x: Double,y: Double): Unit ={
    if(y>600||x>=800||x<0){

    }
    else{
      print(y);
      super.move(x,y);
    }
  }
}
