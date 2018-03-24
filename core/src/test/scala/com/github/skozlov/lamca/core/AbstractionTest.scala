package com.github.skozlov.lamca.core

import org.scalatest.{FlatSpec, Matchers}

class AbstractionTest extends FlatSpec with Matchers{
	"Abstraction" should "be right-associative" in {
		val x = Variable("x")
		val y = Variable("y")
		val z = Variable("z")
		val body: Term = Variable("a")
		Abstraction(Seq(x, y, z), body) shouldBe Abstraction(x, Abstraction(y, Abstraction(z, body)))
	}
}