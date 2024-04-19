package com.sopt.now.compose.feature.signin

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
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
import com.sopt.now.compose.ext.addFocusCleaner
import com.sopt.now.compose.ext.noRippleClickable
import com.sopt.now.compose.model.User
import com.sopt.now.compose.ui.theme.NOWSOPTAndroidTheme
import kotlinx.coroutines.launch

@Composable
fun SignInRoute(
    onSignUpClick: () -> Unit,
    onMainClick: () -> Unit,
    viewModel: SignInViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    LaunchedEffect(viewModel.sideEffect, lifecycleOwner) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle = lifecycleOwner.lifecycle)
            .collect { sideEffect ->
                when (sideEffect) {
                    SignInSideEffect.NavigateToMain -> onMainClick()

                    SignInSideEffect.NavigateToSignUp -> onSignUpClick()

                    is SignInSideEffect.SnackBar -> {
                        scope.launch {
                            snackBarHostState.currentSnackbarData?.dismiss()
                            snackBarHostState.showSnackbar(context.getString(sideEffect.message))
                        }
                    }
                }

            }
    }

    LaunchedEffect(true) {
        viewModel.setInfo()
    }

    SignInScreen(
        snackBarHostState,
        id = state.id,
        pw = state.pw,
        signInBtnClicked = {
            scope.launch {
                viewModel.signInBtnClicked()
            }
        },
        signUpBtnClicked = {
            scope.launch {
                viewModel.signUpBtnClicked()
            }
        },
        fetchId = { viewModel.fetchId(it) },
        fetchPw = { viewModel.fetchPw(it) }
    )
}

@Composable
fun SignInScreen(
    snackBarHostState: SnackbarHostState,
    id: String,
    pw: String,
    signInBtnClicked: () -> Unit,
    signUpBtnClicked: () -> Unit,
    fetchId: (String) -> Unit,
    fetchPw: (String) -> Unit
) {
    Scaffold(
        modifier = Modifier.addFocusCleaner(LocalFocusManager.current),
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
                    text = stringResource(id = R.string.sign_in_title),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(bottom = 30.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = signInBtnClicked
                ) {
                    Text(text = stringResource(id = R.string.sign_in_title))
                }
                Text(
                    modifier = Modifier.noRippleClickable {
                        signUpBtnClicked()
                    },
                    text = stringResource(id = R.string.sign_up_btn),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Thin,
                    color = Color.LightGray
                )
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
        }
    }
}
