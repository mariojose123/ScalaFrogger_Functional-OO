package frogger.screen.gamescene

class IDOBJETO[T]  () {
  private var _id: T=asInstanceOf[T]
  private var _idName:String =""
  def id = _id
  def id_= (newVal:T) = {
    _id = id
    try {
      idName=id.toString()
    }
    catch{
      case e:java.lang.Error=> print(e.getMessage)
    }
  }
  private def idName:String = _idName
  private def idName_=(newVal:String)={
    _id = id
  }
}
