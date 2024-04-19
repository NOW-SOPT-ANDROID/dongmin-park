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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.sopt.now.compose.R
import com.sopt.now.compose.component.textfield.TextFieldWithTitle
import com.sopt.now.compose.model.User
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import kotlinx.coroutines.launch

@Composable
fun SignUpRoute(
    onSignInClick: () -> Unit,
    viewModel: SignUpViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycleOwner.lifecycle).collect { sideEffect ->
            when (sideEffect) {
                SignUpSideEffect.NavigateToSignIn -> {
                    val user = User(
                        id = state.id,
                        pw = state.pw,
                        nickname = state.nickname,
                        juryang = state.juryang
                    )

                    viewModel.setUserData(user)

                    onSignInClick()
                }

                is SignUpSideEffect.SnackBar -> {
                    scope.launch {
                        snackBarHostState.currentSnackbarData?.dismiss()
                        snackBarHostState.showSnackbar(context.getString(sideEffect.message))
                    }
                }
            }

        }
    }

    SignUpScreen(
        snackBarHostState = snackBarHostState,
        id = state.id,
        pw = state.pw,
        nickname = state.nickname,
        juryang = state.juryang,
        fetchId = { viewModel.fetchId(it) },
        fetchPw = { viewModel.fetchPw(it) },
        fetchNickname = { viewModel.fetchNickname(it) },
        fetchJuryang = { viewModel.fetchJuryang(it) },
        signUpBtnClicked = {
            scope.launch {
                viewModel.signUpBtnClicked()
            }
        }
    )
}

@Composable
fun SignUpScreen(
    snackBarHostState: SnackbarHostState,
    id: String,
    pw: String,
    nickname: String,
    juryang: String,
    fetchId: (String) -> Unit,
    fetchPw: (String) -> Unit,
    fetchNickname: (String) -> Unit,
    fetchJuryang: (String) -> Unit,
    signUpBtnClicked: () -> Unit,
) {
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
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
                title = stringResource(id = R.string.juryang),
                value = juryang,
                hint = stringResource(id = R.string.juryang_hint),
                singleLine = true,
                onValueChanged = { juryang ->
                    fetchJuryang(juryang)
                }
            )
        }
    }
}

@Preview
@Composable
fun SignUpScreenPreview() {
    NOWSOPTAndroidTheme {
        // SignUpScreen()
    }
}
