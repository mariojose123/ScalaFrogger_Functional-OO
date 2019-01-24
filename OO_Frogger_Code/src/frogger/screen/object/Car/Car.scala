package frogger.screen.`object`.Car
import javafx.scene.paint.Color
import javafx.scene.Node
import frogger.screen.`object`.Frog.RetangleObject

class Car(height:java.lang.Double , width: java.lang.Double , color: Color, MinSpawnY: java.lang.Double , MaxSpawnY:java.lang.Double ) extends RetangleObject {
  private var _PositionY: Double = 0.0

  def PositionY_=(value: Double): Unit = _PositionY = {if (value<335) {
    throw new ExceptionInInitializerError()
  }
  else {
     value
  }
}
  def PositionY = _PositionY
  var right:Boolean=true;
  try
  {
    setGameObjectRectangle(height,width, color)
  }
  catch {
    case e:ExceptionInInitializerError => print("ERRO DE INICIALIZAÇÃO")
  }
    var RandomPosition = math.random()
    private var Velo:java.lang.Double=0.0
    Velo=math.ceil(RandomPosition * (MaxSpawnY-MinSpawnY+1));
    if(math.ceil(RandomPosition * (MaxSpawnY-MinSpawnY+1))%2==0){
      right=true;
      try {
        PositionY = (0.1 * (math.ceil(RandomPosition * (MaxSpawnY - MinSpawnY + 1)) + (MinSpawnY - 1)) * 14) * 40
      }
      catch{
        case e:ExceptionInInitializerError => print("ERRO DE INICIALIZAÇÃO D")
      }
      move(800, PositionY)
    }
    else{
      right=false
      try {
        PositionY = (0.1 * (math.ceil(RandomPosition * (MaxSpawnY - MinSpawnY + 1)) + (MinSpawnY - 1)) * 14) * 40
      }
      catch{
        case e:ExceptionInInitializerError => print("ERRO DE INICIALIZAÇÃO D")
      }
      move(NodeX(), PositionY)
    }
  def move( ): Unit =
  {
    if(right) {
      move(NodeX() - 1 * Velo, NodeY())
    }
    else{
      move(NodeX() + 1 * Velo, NodeY())
    }
  }
}
