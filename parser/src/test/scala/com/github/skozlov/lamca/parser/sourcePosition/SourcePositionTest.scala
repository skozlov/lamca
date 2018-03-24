package com.github.skozlov.lamca.parser.sourcePosition

import org.scalatest.{FlatSpec, Matchers}

class SourcePositionTest extends FlatSpec with Matchers{
	"Source position" should "should not be greater or less than the equal one" in {
		(SourcePosition(2, 4) compare SourcePosition(2, 4)) shouldBe 0
	}

	it should "be greater than the one with less line" in {
		SourcePosition(1, 0) should be > SourcePosition(0, 2)
	}

	it should "be greater than the one with the same line, but less char index" in {
		SourcePosition(0, 1) should be > SourcePosition(0, 0)
	}
}