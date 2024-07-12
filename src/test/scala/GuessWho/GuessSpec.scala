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
  val vicky: Character = Character(name = "Vicky", hasHair = true, hasFacialHair = false, hasGlasses = false, hasHat = true, gender = Gender.FEMALE, eyeColour = EyeColour.BROWN, hairColour = HairColour.RED)

  val guessJamie:  Guess = new Guess(jamie)
  val guessTom:  Guess = new Guess(tom)
  val guessVicky: Guess = new Guess(vicky)

  // Does Character have hair test
  "guessHasHair" should "return true if character has hair, false if they don't" in {
    assert(!guessJamie.guessHasHair)
    assert(guessTom.guessHasHair)
    assert(guessVicky.guessHasHair)
  }

  // Does Character have facial hair test
  "hasFacialHair" should "return true if character has facial hair, false if they don't" in {
    assert(guessJamie.guessHasFacialHair)
    assert(!guessTom.guessHasFacialHair)
    assert(!guessVicky.guessHasFacialHair)
  }

  // Does Character have glasses test
  "guessGlasses" should "return true if character has glasses, false if they don't" in {
    assert(guessJamie.guessHasGlasses)
    assert(guessTom.guessHasGlasses)
    assert(!guessVicky.guessHasGlasses)
  }

  // Does Character has Hat test
  "guessHat" should "return true if character has hat, false if they don't" in {
    assert(guessJamie.guessHasHat)
    assert(!guessTom.guessHasHat)
    assert(guessVicky.guessHasHat)
  }

  // Is character Male or Female
  "guessGender" should "return true if both character and guess is Male, and vice versa" in {
    assert(guessJamie.guessGender("MALE"))
    assert(!guessTom.guessGender("FEMALE"))
    assert(guessVicky.guessGender("FEMALE"))
  }

  // What eye colour does the character have
  "guessEyeColour" should "return true if correct eye colour is guessed" in {
    assert(guessJamie.guessEyeColour("BLUE"))
    assert(guessTom.guessEyeColour("GREEN"))
    assert(guessVicky.guessEyeColour("BROWN"))
    assert(!guessJamie.guessEyeColour("GREEN"))
    assert(!guessTom.guessEyeColour("RED"))
    assert(!guessVicky.guessEyeColour("PURPLE"))
  }

  // What hair colour does the character have
  "guessHairColour" should "return true if correct hair colour is guessed" in {
    assert(guessJamie.guessHairColour("BLONDE"))
    assert(guessTom.guessHairColour("BRUNETTE"))
    assert(guessVicky.guessHairColour("RED"))
    assert(!guessJamie.guessHairColour("YELLOW"))
    assert(!guessTom.guessHairColour("RED"))
    assert(!guessVicky.guessHairColour("PURPLE"))
  }
}
