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




  //  #######################################
  //  #       FOUNDATIONAL GUESS LOGIC      #
  //  #######################################

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
    assert(guessJamie.guessGender("male"))
    assert(guessJamie.guessGender("MaLe"))
    assert(!guessTom.guessGender("FEMALE"))
    assert(guessVicky.guessGender("FEMALE"))
  }

  // What eye colour does the character have
  "guessEyeColour" should "return true if correct eye colour is guessed" in {
    assert(guessJamie.guessEyeColour("BROWN"))
    assert(guessTom.guessEyeColour("GREEN"))
    assert(guessVicky.guessEyeColour("BROWN"))
    assert(!guessJamie.guessEyeColour("GREEN"))
    assert(!guessTom.guessEyeColour("RED"))
    assert(!guessVicky.guessEyeColour("PURPLE"))
  }

  // What hair colour does the character have
  "guessHairColour" should "return true if correct hair colour is guessed" in {
    assert(guessJamie.guessHairColour("BLONDE"))
    assert(guessTom.guessHairColour("BLONDE"))
    assert(guessVicky.guessHairColour("RED"))
    assert(!guessJamie.guessHairColour("YELLOW"))
    assert(!guessTom.guessHairColour("RED"))
    assert(!guessVicky.guessHairColour("PURPLE"))
  }

  // Guess name of character
  "guessName" should "respond true or false depending on guess" in {
    assert(guessJamie.guessName("jamie"))
    assert(!guessJamie.guessName("tom"))
  }

  //  #######################################
  //  #               GAME LOGIC            #
  //  #######################################

  //  Game test to make sure of chosen player

  // Game tests for any character attribute that matches chose character
  "filterRemaining" should "remove any character attributes that don't match chosen character" in {
    // checks for hair
    assert(testGame.filterRemaining(1) == Seq(jamie, jane))
    // checks for facial hair
    assert(testGame.filterRemaining(2) == Seq(jamie))
    // checks for glasses
    assert(testGame.filterRemaining(3) == Seq(tom, jamie))
    // checks for hat
    assert(testGame.filterRemaining(4) == Seq(jamie, vicky))
    // check for gender
    assert(testGame.filterRemaining(5) == Seq(tom, jamie))
    // check for eye colour BLUE
    assert(testGame.filterRemaining(6) == Seq(tom, jamie, vicky))
    // GREEN
    assert(testGame.filterRemaining(7) == Seq(jamie, vicky, jane))
    // BROWN
    assert(testGame.filterRemaining(8) == Seq(jamie, vicky))
    // check for hair colour BRUNETTE )
    assert(testGame.filterRemaining(9) == Seq(tom, jamie, vicky, jane))
    // BLONDE
    assert(testGame.filterRemaining(10) == Seq(tom,jamie))
    // RED
    assert(testGame.filterRemaining(11) == Seq(tom,jamie, jane))
    // GUESS NAME
    assert(testGame.filterRemaining(12, "vicky") == (Seq(tom,jamie,jane), false))
    assert(testGame.filterRemaining(12, "jamie") == (Seq(jamie), true))
  }

  //  #######################################
  //  #               EXTENSION 1           #
  //  #######################################

  // Limited on testing due to I/O functions

  "displayCharacters" should "Display list of characters and their attributes" in {
    assert(interface.displayCharacters(characters) == println(s"Number of remaining characters: ${characters.length}"))
  }

  "validate_choice" should "returns an int if input is also a number" in {
    assert(interface.validate_choice("5") == 5)
    assert(interface.validate_choice("0") == 0)
    assert(interface.validate_choice("-1") == -1)
  }
    /** --- Tests we would implement if we knew how :P ---
     * Due to using CLI functions, we were not able to implement TDD for the following methods:
     * interface.get_user_input
     * interface.get_attribute_choice
     * interface.validate_choice
     * interface.get_name_choice
     * interface.get_question_choice
     * interface.check_user_guess
     */


  //  #######################################
  //  #               EXTENSION 2           #
  //  #######################################
  "remove_random_character" should "removes random character (who isn't chosen player) from remaining players" in {
    val removedRandomCharacterList:Seq[Character] = testGame.remove_random_character()
    assert(removedRandomCharacterList.contains(jamie))
    assert(removedRandomCharacterList.length == characters.length-1)
  }
  "display_random_letter" should "displays random letter from chosen characters name" in {
     testGame.display_random_letter()
    assert(testGame.get_update_message() == (s"Hint: Name contains the letter 'j' ")
    || testGame.get_update_message() == (s"Hint: Name contains the letter 'a' ")
    || testGame.get_update_message() == (s"Hint: Name contains the letter 'm' ")
    || testGame.get_update_message() == (s"Hint: Name contains the letter 'i' ")
    || testGame.get_update_message() == (s"Hint: Name contains the letter 'e' "))
  }

//  Hard to do tests will think about

}

