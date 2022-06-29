package com.example.codingchallenge

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.nyc.school.repository.MainRepository
import com.nyc.school.repository.RetrofitService
import com.nyc.school.viewmodel.MainViewModel
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private val retrofitService = RetrofitService.getInstance()
    private var viewModel: MainViewModel = MainViewModel(MainRepository(retrofitService))

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.nyc.school", appContext.packageName)
    }

    @Test
    fun setup(){
        viewModel.getAllImages()
    }
    @Test
    fun checkAllSchoolResponse() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.nyc.school", appContext.packageName)
        assert(viewModel.schoolList.value != null)
    }
}