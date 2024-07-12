

sealed trait EyeColour
case object BLUE extends EyeColour
case object GREEN extends EyeColour
case object BROWN extends EyeColour

sealed trait HairColour
case object BLONDE extends HairColour
case object BRUNETTE extends HairColour
case object RED extends HairColour


sealed trait Gender
case object MALE extends Gender
case object FEMALE extends Gender

// FUCK YESS GITHUB WORKING
case class Character(
                      name: String,
                      hasHair: Boolean,
                      hasFacialHair: Boolean,
                      hasGlasses: Boolean,
                      hasHat: Boolean,
                      gender:Gender,
                      eyeColour: EyeColour,
                      hairColour: HairColour
                    )

val Tom: Character = Character(name = "Tom", hasHair = true, hasFacialHair = false, hasGlasses = true, hasHat = false, gender = MALE, eyeColour = GREEN , hairColour = BRUNETTE)
val Jamie: Character = Character(name = "Jamie", hasHair = false, hasFacialHair = true, hasGlasses = true, hasHat = true, gender = MALE, eyeColour = BLUE, hairColour = BLONDE)


