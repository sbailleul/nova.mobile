package com.esgi.nova.parameters.application

import com.esgi.nova.parameters.infrastructure.storage.ParametersStorageRepository
import com.esgi.nova.parameters.ports.ILanguageParameters
import com.esgi.nova.sound.application.SwitchSound
import javax.inject.Inject

class SaveParameters @Inject constructor(
    private val selectLanguage: SelectLanguage,
    private val parametersStorageRepository: ParametersStorageRepository,
    private var switchTheme: SwitchTheme,
    private var switchSound: SwitchSound
) {

    fun execute(params: ILanguageParameters): ILanguageParameters {
        parametersStorageRepository.save(params)
        switchTheme.execute(params.isDarkMode)
        switchSound.execute(params.hasSound)

        params.selectedLanguage?.let { language ->
            selectLanguage.execute(languageId = language.id)?.let { selectedLanguage ->
                return params.toLanguageParameters(selectedLanguage)
            }
        }
        return params
    }

}