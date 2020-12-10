package com.jnfran92.kotcoin.di.component

import com.jnfran92.kotcoin.di.PerActivity
import com.jnfran92.kotcoin.di.module.ActivityModule
import com.jnfran92.kotcoin.di.module.CryptoModule
import com.jnfran92.kotcoin.ui.crypto.fragment.CryptoListFragment
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class],
    modules =[CryptoModule::class, ActivityModule::class])
interface CryptoComponent: ActivityComponent {

    // inject CryptoListFragment
    fun inject(cryptoListFragment: CryptoListFragment)

}