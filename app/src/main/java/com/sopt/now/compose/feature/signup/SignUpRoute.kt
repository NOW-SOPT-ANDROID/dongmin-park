package com.sopt.now.compose.feature.signup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.now.compose.R
import com.sopt.now.compose.component.textfield.TextFieldWithTitle

@Composable
fun SignUpRoute(
    onSignInClick: () -> Unit,
    onShowErrorSnackBar: (Int) -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collect { sideEffect ->
            when (sideEffect) {
                SignUpSideEffect.NavigateToSignIn -> {
                    onSignInClick()
                }

                is SignUpSideEffect.SnackBar -> onShowErrorSnackBar(sideEffect.message)
            }
        }
    }

    SignUpScreen(
        id = state.id,
        pw = state.password,
        nickname = state.nickname,
        phoneNumber = state.phoneNumber,
        fetchId = { viewModel.fetchId(it) },
        fetchPw = { viewModel.fetchPw(it) },
        fetchNickname = { viewModel.fetchNickname(it) },
        fetchPhoneNumber = { viewModel.fetchPhoneNumber(it) },
        signUpBtnClicked = {
                viewModel.signUpBtnClicked()

        }
    )
}

@Composable
fun SignUpScreen(
    id: String,
    pw: String,
    nickname: String,
    phoneNumber: String,
    fetchId: (String) -> Unit,
    fetchPw: (String) -> Unit,
    fetchNickname: (String) -> Unit,
    fetchPhoneNumber: (String) -> Unit,
    signUpBtnClicked: () -> Unit,
) {
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(40.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.sign_up_title),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        },
        bottomBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 30.dp)
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = signUpBtnClicked
                ) {
                    Text(text = stringResource(id = R.string.sign_up_btn))
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextFieldWithTitle(
                title = stringResource(id = R.string.id),
                value = id,
                hint = stringResource(id = R.string.id_hint),
                singleLine = true,
                onValueChanged = { id ->
                    fetchId(id)
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            TextFieldWithTitle(
                title = stringResource(id = R.string.pw),
                value = pw,
                singleLine = true,
                keyboardType = KeyboardType.Password,
                hint = stringResource(id = R.string.pw_hint),
                onValueChanged = { pw ->
                    fetchPw(pw)
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            TextFieldWithTitle(
                title = stringResource(id = R.string.nickname),
                value = nickname,
                hint = stringResource(id = R.string.nickname_hint),
                singleLine = true,
                onValueChanged = { nickname ->
                    fetchNickname(nickname)
                }
            )

            Spacer(modifier = Modifier.height(30.dp))

            TextFieldWithTitle(
                title = stringResource(id = R.string.phone_number),
                value = phoneNumber,
                hint = stringResource(id = R.string.phone_number_hint),
                singleLine = true,
                onValueChanged = { number ->
                    fetchPhoneNumber(number)
                }
            )
        }
    }
}
