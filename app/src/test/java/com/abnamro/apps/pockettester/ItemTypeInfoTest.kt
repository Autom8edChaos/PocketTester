package com.abnamro.apps.pockettester

import android.os.Parcel
import org.junit.Test

import org.junit.Assert.*

/**
 * Example of a local unit test using the Kotlin language
 * This test runs in isolation. The items do no have any connection to a storage container and
 * can be created without interfering with other data
 */
class ItemTypeInfoTest {

    @Test
    fun givenAnItem_whenGettingTopicId_thenCorrect() {
        var item = ItemTypeInfo("topicIdTest", "Topic for the topic id test", null)
        assertEquals(item.topicId, "topicIdTest")
    }

    @Test
    fun givenAnItem_whenGettingTopicTitle_thenCorrect() {
        var item = ItemTypeInfo("topicIdTest", "Topic for the topic title test", null)
        assertEquals(item.title, "Topic for the topic title test")
    }

    @Test
    fun givenAnItem_whenGettingTheStringValue_sameAsTitle() {
        var item = ItemTypeInfo("topicIdTest", "Topic for the topic name test", null)
        assertEquals(item.toString(), "Topic for the topic name test")
    }
}
