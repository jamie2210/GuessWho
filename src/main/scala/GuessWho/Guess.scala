package GuessWho

/** name: String,
 hasHair: Boolean,
 hasFacialHair: Boolean,
 hasGlasses: Boolean,
 hasHat: Boolean,
 gender:Gender,
 eyeColour: EyeColour,
 hairColour: HairColour */

class Guess (character:Character){

  def guessHasHair: Boolean = {
    character.hasHair
  }
  def guessHasFacialHair:Boolean = {
    character.hasFacialHair
  }
  def guessHasGlasses: Boolean = {
    character.hasGlasses
  }
  def guessHasHat: Boolean = {
    character.hasHat
  }


  def guessGender(guess:String): Boolean = { ??? }

  def guessEyeColour(guess:String): Boolean = { ??? }

  def guessHairColour(guess:String): Boolean = { ??? }

}
