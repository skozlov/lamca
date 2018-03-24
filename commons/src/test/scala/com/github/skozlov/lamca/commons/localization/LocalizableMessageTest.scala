package com.github.skozlov.lamca.commons.localization

import org.scalatest.{FlatSpec, Matchers}

class LocalizableMessageTest extends FlatSpec with Matchers{
	"Localizable message resolution" should "substitute parameters" in {
		val message = LocalizableMessage(
			key = "some.key",
			defaultTemplate = "abc ${abc} def ${def}",
			parameters = Map("abc" -> "123", "def" -> "456")
		)
		message.resolved() shouldBe "abc 123 def 456"
	}

	it should "not require all parameters from the default template to be provided or all provided parameters to be present in the default template" in {
		val message = LocalizableMessage(
			key = "some.key",
			defaultTemplate = "abc ${abc} def ${def}",
			parameters = Map("abc" -> "123", "ef" -> "456")
		)
		message.resolved() shouldBe "abc 123 def ${def}"
	}

	it should "support literal $ as $$" in {
		val message = LocalizableMessage(
			key = "some.key",
			defaultTemplate = "abc ${abc} def $${def}",
			parameters = Map("abc" -> "123", "def" -> "456")
		)
		message.resolved() shouldBe "abc 123 def ${def}"
	}
}