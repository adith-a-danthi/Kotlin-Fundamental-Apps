package com.example.daggerexample.registration

import com.example.daggerexample.registration.enterdetails.EnterDetailsFragment
import com.example.daggerexample.registration.termsandconditions.TermsAndConditionsFragment
import dagger.Subcomponent

@Subcomponent
interface RegistrationComponent {

    // Factory to create instances of RegistrationComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): RegistrationComponent
    }

    fun inject(activity: RegistrationActivity)
    fun inject(fragment: EnterDetailsFragment)
    fun inject(fragment: TermsAndConditionsFragment)
}