package GuessWho

import org.scalatest.flatspec.AnyFlatSpec

class GameBoardSpec extends AnyFlatSpec{
  val jamie: Character = Character(name = "Jamie", hasHair = false, hasFacialHair = true, hasGlasses = true, hasHat = true, gender = Gender.MALE, eyeColour = EyeColour.BROWN, hairColour = HairColour.BLONDE)
  val tom: Character = Character(name = "Tom", hasHair = true, hasFacialHair = false, hasGlasses = true, hasHat = false, gender = Gender.MALE, eyeColour = EyeColour.GREEN, hairColour = HairColour.BLONDE)
  val vicky: Character = Character(name = "Vicky", hasHair = true, hasFacialHair = false, hasGlasses = false, hasHat = true, gender = Gender.FEMALE, eyeColour = EyeColour.BROWN, hairColour = HairColour.RED)
  val jane: Character = Character(name = "Jane", hasHair = false, hasFacialHair = false, hasGlasses = false, hasHat = false, gender = Gender.FEMALE, eyeColour = EyeColour.BLUE, hairColour = HairColour.NONE)
  val guessJamie: Guess = new Guess(jamie)
  val guessTom: Guess = new Guess(tom)
  val guessVicky: Guess = new Guess(vicky)

  val characters: Seq[Character] = Seq(tom, jamie, vicky, jane)

  val testGame: GameBoard = new GameBoard(characters = characters, defaultChosenCharacter = Some(jamie))
  val interface: Interface = new Interface()

  "filterRemaining" should "remove any character attributes that don't match chosen character" in {
    // checks for hair
    assert(testGame.filterRemaining(Question.hasHairQuestion) == Seq(jamie, jane))
    // checks for facial hair
    assert(testGame.filterRemaining(Question.hasFacialHairQuestion) == Seq(jamie))
    // checks for glasses
    assert(testGame.filterRemaining(Question.hasGlassesQuestion) == Seq(tom, jamie))
    // checks for hat
    assert(testGame.filterRemaining(Question.hasHatQuestion) == Seq(jamie, vicky))
    // check for gender
    assert(testGame.filterRemaining(Question.MaleGenderQuestion) == Seq(tom, jamie))
    assert(testGame.filterRemaining(Question.FemaleGenderQuestion) == Seq(tom, jamie))
    // check for eye colour BLUE
    assert(testGame.filterRemaining(Question.BlueEyesQuestion) == Seq(tom, jamie, vicky))
    // GREEN
    assert(testGame.filterRemaining(Question.GreenEyesQuestion) == Seq(jamie, vicky, jane))
    // BROWN
    assert(testGame.filterRemaining(Question.BrownEyesQuestion) == Seq(jamie, vicky))
    // check for hair colour BRUNETTE )
    assert(testGame.filterRemaining(Question.BrunetteHairQuestion) == Seq(tom, jamie, vicky, jane))
    // BLONDE
    assert(testGame.filterRemaining(Question.BlondeHairQuestion) == Seq(tom,jamie))
    // RED
    assert(testGame.filterRemaining(Question.RedHairQuestion) == Seq(tom,jamie, jane))
    // GUESS NAME
    assert(testGame.filterRemaining("vicky") == (Seq(tom,jamie,jane), false))
    assert(testGame.filterRemaining("jamie") == (Seq(jamie), true))
  }

  "display_random_letter" should "displays random letter from chosen characters name" in {
    testGame.display_random_letter()
    assert(testGame.get_update_message() == (s"Hint: Name contains the letter 'j' ")
      || testGame.get_update_message() == (s"Hint: Name contains the letter 'a' ")
      || testGame.get_update_message() == (s"Hint: Name contains the letter 'm' ")
      || testGame.get_update_message() == (s"Hint: Name contains the letter 'i' ")
      || testGame.get_update_message() == (s"Hint: Name contains the letter 'e' "))
  }

  "remove_random_character" should "removes random character (who isn't chosen player) from remaining players" in {
    val removedRandomCharacterList:Seq[Character] = testGame.remove_random_character()
    assert(removedRandomCharacterList.contains(jamie))
    assert(removedRandomCharacterList.length == characters.length-1)
  }

  "filterByEyeColour" should
    "filter out characters depending on eye colour" in {
      //Blue
      assert(testGame.filterByEyeColour(EyeColour.BLUE) == Seq(tom, jamie, vicky))
      // GREEN
      assert(testGame.filterByEyeColour(EyeColour.GREEN) == Seq(jamie, vicky, jane))
      // BROWN
      assert(testGame.filterByEyeColour(EyeColour.BROWN) == Seq(jamie, vicky))

  }

  "filterByHairColour" should
  "filter out characters depending on hair colour" in {
    // check for hair colour BRUNETTE )
    assert(testGame.filterByHairColour(HairColour.BRUNETTE) == Seq(tom, jamie, vicky, jane))
    // BLONDE
    assert(testGame.filterByHairColour(HairColour.BLONDE) == Seq(tom,jamie))
    // RED
    assert(testGame.filterByHairColour(HairColour.RED) == Seq(tom,jamie, jane))
  }

  "filterByGender" should
  "filter out characters depending on gender" in {
    assert(testGame.filterByGender(Gender.MALE) == Seq(tom, jamie))
    assert(testGame.filterByGender(Gender.FEMALE) == Seq(tom, jamie))
  }
}
