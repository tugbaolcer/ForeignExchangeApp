package com.tugbaolcer.foreignexchangeapp.presentation.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tugbaolcer.foreignexchangeapp.R
import com.tugbaolcer.foreignexchangeapp.presentation.component.CustomButton
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.BlackTwoColor
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.BottomCardShape
import com.tugbaolcer.foreignexchangeapp.presentation.ui.theme.CharcoalColor
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@DelicateCoroutinesApi
@Composable
fun OnBoardingPager(
    item: List<OnboardingData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    navController: NavController
    ) {

    Box(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(BlackTwoColor),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Image(
                        painter = painterResource(id = item[page].image),
                        contentDescription = item[page].title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .padding(top = 100.dp)
                    )
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(360.dp),
                colors = CardDefaults.cardColors(CharcoalColor),
                elevation = CardDefaults.elevatedCardElevation(0.dp),
                shape = BottomCardShape.large
            ) {
                Box {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.height(360.dp)
                    ) {
                        PagerIndicator(currentPage = pagerState.currentPage)
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 30.dp, start = 30.dp)
                                .wrapContentWidth(Alignment.CenterHorizontally),
                            text = item[pagerState.currentPage].title,
                            color = Color.White,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                        )

                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                            text = item[pagerState.currentPage].description,
                            color = Color.White,
                            fontSize = 17.sp
                        )
                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(30.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if (pagerState.currentPage != 2) {
                                TextButton(onClick = {
                                    navController.navigate("containerPage")
                                }) {
                                    Text(
                                        text = "Skip Now",
                                        color = Color.White,
                                        textAlign = TextAlign.Right,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                }

                                OutlinedButton(
                                    onClick = {
                                        GlobalScope.launch {
                                            pagerState.scrollToPage(
                                                pagerState.currentPage + 1
                                            )
                                        }
                                    },
                                    border = BorderStroke(
                                        14.dp,
                                        Color.White
                                    ),
                                    shape = RoundedCornerShape(50),
                                    colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Gray),
                                    modifier = Modifier.size(65.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_right_arrow),
                                        contentDescription = "",
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            } else {
                                CustomButton(buttonText = "Get Started",
                                    onCustomClick = {
                                        navController.navigate("containerPage")
                                    })
                            }
                        }
                    }
                }
            }
        }
    }
}


//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//@Preview(showBackground = true)
//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
//fun OnBoardingPagerPreview() {
//    ForeignExchangeAppTheme {
//        val pagerState = rememberPagerState(
//            pageCount = { 3 },
//            initialPage = 0,
//            initialPageOffsetFraction = 0f
//        )
//
//        OnBoardingPager(
//            item = pages,
//            pagerState = pagerState,
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.Blue)
//        )
//    }
//}