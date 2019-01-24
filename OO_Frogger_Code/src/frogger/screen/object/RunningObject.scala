package frogger.screen.`object`

import frogger.screen.`object`.Frog.RetangleObject
import javafx.scene.paint.Color

abstract class LakeObject(private var height:java.lang.Double , private var width: java.lang.Double ,private var color: Color, private var  _positionY: java.lang.Double, private var right:java.lang.Boolean)extends RetangleObject {

  def move( Velo : java.lang.Double): Unit =
  {
    if(right) {
      super.move(NodeX() - 1 * Velo, NodeY())
    }
    else{
      super.move(NodeX() + 1 * Velo, NodeY())
    }
  }
  def positionY_= (value:Double):Unit = _positionY = value
  def positionY:Double = _positionY
}
