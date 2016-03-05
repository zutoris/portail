package controllers

import play.api._
import play.api.mvc._
import java.net._;
import java.io._;

class Application extends Controller {

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def musicHomePage = Action {
    Ok(views.html.musique())
  }

  def videoHomePage = Action {
    Ok(views.html.videoP())
  }

	/** Remplissage de la page des congés */
	def congesHomePage = Action {
		val googleDrive = new URL("https://docs.google.com/spreadsheets/d/1X-yqIgRdGK-VlBMcqLzF_xx2tlP_ylJypmemokhX4ak/edit?usp=sharing")
		val in = new BufferedReader(new InputStreamReader(googleDrive.openStream()))
		var inputLine = in.readLine()
		var nbConges="-1".toDouble
		var nbRtt="-1".toDouble
		var textFound = false
		while (inputLine != null && !textFound){
			val researchedText = "CP restants : "
			val textLength = researchedText.length()
			val indexFound = inputLine.indexOf(researchedText)
			if (indexFound > 0){
				val index2Found = inputLine.indexOf("_", indexFound+textLength)
				nbConges = inputLine.substring(indexFound+textLength, index2Found).replace(',','.').toDouble
				nbRtt = getNbRttRestants(inputLine)
				textFound = true
			}
			inputLine = in.readLine()
		}
		in.close()
		
		Ok(views.html.conges(nbConges, nbRtt))
	}

	/** Récupération du nombre de RTT restants */
	def getNbRttRestants(inputLine:String) : Double = {
		val researchedRttText = "RTT restants : "
		val textRttLength = researchedRttText.length()
		val indexRttFound = inputLine.indexOf(researchedRttText)
		if (indexRttFound > 0){
			val index2RttFound = inputLine.indexOf("_", indexRttFound+textRttLength)
			val nbRttInString = inputLine.substring(indexRttFound+textRttLength, index2RttFound)
			return nbRttInString.replace(',','.').toDouble
		} else {
			return -1
		}
	}

}
