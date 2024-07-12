package GuessWho

import org.scalatest.FlatSpec

/** name: String,
 hasHair: Boolean,
 hasFacialHair: Boolean,
 hasGlasses: Boolean,
 hasHat: Boolean,
 gender:Gender,
 eyeColour: EyeColour,
 hairColour: HairColour */

class GuessSpec extends FlatSpec {
  val jamie: Character = Character(name = "Jamie", hasHair = false, hasFacialHair = true, hasGlasses = true, hasHat = true, gender = Gender.MALE, eyeColour = EyeColour.BLUE, hairColour = HairColour.BLONDE)
  val tom: Character = Character(name = "Tom", hasHair = true, hasFacialHair = false, hasGlasses = true, hasHat = false, gender = Gender.MALE, eyeColour = EyeColour.GREEN, hairColour = HairColour.BRUNETTE)
  val guessJamie:  Guess = new Guess(jamie)
  val guessTom:  Guess = new Guess(tom)
  // Does Character have hair test
  "guessHasHair" should "return true if character has hair, false if they don't" in {
    assert(!guessJamie.guessHasHair)
    assert(guessTom.guessHasHair)
  }
}
