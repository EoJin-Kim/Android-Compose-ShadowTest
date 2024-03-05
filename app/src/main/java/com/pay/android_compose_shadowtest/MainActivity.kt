package com.pay.android_compose_shadowtest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.gigamole.composeshadowsplus.common.ShadowsPlusDefaults
import com.gigamole.composeshadowsplus.rsblur.RSBlurShadowDefaults
import com.gigamole.composeshadowsplus.softlayer.SoftLayerShadowContainer
import com.gigamole.composeshadowsplus.softlayer.softLayerShadow
import com.pay.android_compose_shadowtest.ui.theme.AndroidComposeShadowTestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidComposeShadowTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var shadowType by remember { mutableStateOf(ShadowType.RSBlur) }
    var shadowShapeType by remember { mutableStateOf(ShadowShapeType.Rect) }

    var shadowRadius by remember { mutableStateOf(ShadowsPlusDefaults.ShadowRadius) }
    var shadowColor by remember { mutableStateOf(ShadowsPlusDefaults.ShadowColor) }
    var shadowSpread by remember { mutableStateOf(10.dp) }
    var shadowOffsetX by remember { mutableStateOf(ShadowsPlusDefaults.ShadowOffset.x) }
    var shadowOffsetY by remember { mutableStateOf(ShadowsPlusDefaults.ShadowOffset.y) }
    var rsBlurShadowAlignRadius by remember { mutableStateOf(RSBlurShadowDefaults.RSBlurShadowIsAlignRadius) }
    var isAlphaContentClip by remember { mutableStateOf(false) }
    var alphaContentColor by remember { mutableStateOf(Color.White) }
    val shadowOffset by remember(shadowOffsetX, shadowOffsetY) {
        derivedStateOf {
            DpOffset(
                x = 0.dp,
                y = 0.dp
            )
        }
    }
    val shadowShape by remember(shadowShapeType) {
        derivedStateOf {
            when (shadowShapeType) {
                ShadowShapeType.Rect -> RectangleShape
                ShadowShapeType.Circle -> CircleShape
                ShadowShapeType.Path -> CutCornerShape(size = 42.dp)
            }
        }
    }
    val contentColor by remember(
        isAlphaContentClip,
        alphaContentColor
    ) {
        derivedStateOf {
            if (isAlphaContentClip) {
                alphaContentColor
            } else {
                Color.White
            }
        }
    }

    SoftLayerShadowContainer {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    color = Color(0xFF252631)
                )

        ) {
            Box(
                modifier = Modifier
                    .padding(80.dp)
                    .fillMaxSize()
                    .softLayerShadow(
                        radius = shadowRadius,
                        color = Color(0x40C3C3C3),
                        shape = RoundedCornerShape(5.dp),
                        spread = 5.dp,
                        offset = shadowOffset,
                        isAlphaContentClip = isAlphaContentClip
                    )
                    .background(
                        color = Color(0xFF252631),
                        shape = RoundedCornerShape(5.dp)
                    )
            ){
                Text("sdfd")
            }
        }
    }
}

enum class ShadowType {
    RSBlur,
    SoftLayer,
    Elevation
}

enum class ShadowShapeType {
    Rect,
    Circle,
    Path
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidComposeShadowTestTheme {
        Greeting("Android")
    }
}