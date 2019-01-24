package frogger.screen.`object`.Log

import frogger.screen.`object`.Frog.RetangleObject
import frogger.screen.`object`.LakeObject
import javafx.scene.paint.Color

class Log (private var height:java.lang.Double ,private var width: java.lang.Double , private var color: Color,private var _positionY: java.lang.Double,private var  _right:java.lang.Boolean) extends LakeObject(height , width , color,_positionY, _right){
  try
  {
    setGameObjectRectangle(height,width, color)
  }
  catch {
    case e:ExceptionInInitializerError => print("ERRO DE INICIALIZAÇÃO")
  }
  var RandomPosition = math.random()
  if (right) {
    move(800, 0.1 *  positionY *14 * 40)
  }
  else {
    move(0, 0.1 *  positionY *14 * 40)
  }
  var initY =0
  if(right){
    initY=800
  }
  else{
    initY=0
  }
  def right = _right
}
