package com.fireworks.myeventsdk.Utils

import com.fireworks.myeventsdk.ArticleCategoryResponse
import com.fireworks.myeventsdk.model.ApiResponse
import com.fireworks.myeventsdk.model.ArticleDetailResponse
import com.fireworks.myeventsdk.model.ArticleListResponse
import com.fireworks.myeventsdk.model.CheckOutResponse
import com.fireworks.myeventsdk.model.CommonResponse
import com.fireworks.myeventsdk.model.EventCategoryResponse
import com.fireworks.myeventsdk.model.EventResponse
import com.fireworks.myeventsdk.model.GetSettingsResponse
import com.fireworks.myeventsdk.model.HonorificListResponse
import com.fireworks.myeventsdk.model.MerchantCategory
import com.fireworks.myeventsdk.model.MerchantRewardResponse
import com.fireworks.myeventsdk.model.PRIVILEGEdASH.MainResponse
import com.fireworks.myeventsdk.model.PRIVILEGEdASH.PrivRankResponse
import com.fireworks.myeventsdk.model.PointDetailResponse
import com.fireworks.myeventsdk.model.Profile.ProfileResponse
import com.fireworks.myeventsdk.model.login.LoginResponse
import com.fireworks.myeventsdk.model.rewards_search.SearchRewardItem
import com.fireworks.myeventsdk.model.Result
import com.fireworks.myeventsdk.model.SubResponse
import com.fireworks.myeventsdk.model.TimerResponse
import com.fireworks.myeventsdk.model.UploadImageResponse
import com.fireworks.myeventsdk.model.VerifyOtpResponse
import com.fireworks.myeventsdk.model.VerifyPhoneNumberResponse
import com.fireworks.myeventsdk.model.country.CountryListResponse
import com.fireworks.myeventsdk.model.directory.DirectoryResponse
import com.fireworks.myeventsdk.model.directory.category.DirectoryCategoryResponse
import com.incredibleqr.mysogo.data.remote.model.ShippingPointResponse
import com.incredibleqr.mysogo.data.remote.model.branch.states.StatesResponse
import com.incredibleqr.mysogo.data.remote.model.dailyReward.DailyRewardResponse
import com.incredibleqr.mysogo.data.remote.model.daily_check_in.DailyCheckInResponse
import com.fireworks.myeventsdk.model.dashboard.DashboardResponse
import com.fireworks.myeventsdk.model.dialogs.VersionChecking
import com.fireworks.myeventsdk.model.directory.detail.DirectoryDetailResponse
import com.fireworks.myeventsdk.model.directory.floor.DirectoryFloorResponse
import com.fireworks.myeventsdk.model.facility.FacilityResponse
import com.fireworks.myeventsdk.model.facility.category.FacilityCategoryResponse
import com.fireworks.myeventsdk.model.facility.detail.FacilityDetailResponse
import com.fireworks.myeventsdk.model.facility.floor.FacilityFloorResponse
import com.fireworks.myeventsdk.model.links.UsefulLinksResponse
import com.fireworks.myeventsdk.model.migratedUser.MigratedUserDataResponse
import com.fireworks.myeventsdk.model.news.NewsResponse
import com.fireworks.myeventsdk.model.news.detail.NewsDetailResponse
import com.fireworks.myeventsdk.model.notifications.NotificationResponse
import com.fireworks.myeventsdk.model.register.RegisterResponse
import com.fireworks.myeventsdk.model.reward.category.FilterData
import com.fireworks.myeventsdk.model.shop.ShopResponse
import com.fireworks.myeventsdk.model.support.SupportResponse
import com.fireworks.myeventsdk.model.transaction.TransactionDetailResponse
import com.fireworks.myeventsdk.model.transaction.TransactionResponse
import com.fireworks.myeventsdk.model.wallet.CheckMigrateUserResponse
import com.incredibleqr.mysogo.data.remote.model.ReferralDataResponse
import com.incredibleqr.mysogo.data.remote.model.inAppAlert.InAppAlertResponse
import com.incredibleqr.mysogo.data.remote.model.mall.MultiMallResponse
import com.incredibleqr.mysogo.data.remote.model.reward.RewardResponse
import com.fireworks.myeventsdk.model.reward.category.RewardCategoryResponse
import com.fireworks.myeventsdk.model.reward.detail.RewardDetailResponse
import com.fireworks.myeventsdk.model.wallet.WalletResponse
import com.fireworks.myeventsdk.model.wallet.detail.WalletDetailResponse
import com.fireworks.myeventsdk.model.wallet.multiple.WalletMultipleResponse
import com.incredibleqr.mysogo.data.remote.model.country_codes.CountryCodesResponse
import com.fireworks.myeventsdk.model.events_detail.Detail
import com.fireworks.myeventsdk.model.login.LoginEmailResponse
import com.fireworks.myeventsdk.model.reward.RewardCategoryTypeResponse

class CommonInterface {


     interface ProfileUpdateCallback {
            fun onSuccess(response: CommonResponse)
            fun onFailure(errorMessage: String)
        }


    interface ProfileCallback {
        fun onSuccess(response: ProfileResponse)
        fun onFailure(errorMessage: String)
    }

    interface LoginCallback {
        fun onSuccess(response: LoginResponse)
        fun onFailure(errorMessage: String)
    }

    interface checkEmailLoginCallback {
        fun onSuccess(response: LoginEmailResponse)
        fun onFailure(errorMessage: String)
    }


    interface VerifyPhoneCallback {
        fun onSuccess(response: VerifyPhoneNumberResponse)
        fun onFailure(errorMessage: String)
    }



  interface LoginOtpCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
    }


  interface sendEmailOtpCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
    }

  interface loginEmailOtpCallback {
        fun onSuccess(response: LoginResponse)
        fun onFailure(errorMessage: String)
    }

  interface registerEmailOtpCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
    }

    interface OtpCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
    }


    interface Callback {
        fun onSuccess(events: EventResponse?)
        fun onFailure(errorMessage: String)
    }

    interface EventDetailCallback {
        fun onSuccess(detail: Detail?)
        fun onFailure(errorMessage: String)
    }

    interface FavouriteCallback {
        fun onSuccess(detail: SubResponse?)
        fun onFailure(errorMessage: String)
    }


    interface CheckoutEventCallback {
        fun onSuccess(detail: CheckOutResponse?)
        fun onFailure(errorMessage: String)
    }


    interface EventCategoryCallback {
        fun onSuccess(detail: EventCategoryResponse?)
        fun onFailure(errorMessage: String)
    }


    interface CategoryCallback {
        fun onSuccess(categories: List<Result>)
        fun onFailure(errorMessage: String)
    }

    interface SearchCallback {
        fun onSuccess(results: List<SearchRewardItem>) // <-- Your actual model
        fun onFailure(errorMessage: String)
    }
    interface AddPointsCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
    }

    interface RatePurchaseCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
    }

    interface DashboardCallback {
        fun onSuccess(response: DashboardResponse)
        fun onFailure(errorMessage: String)
    }

    interface PrivCallback {
        fun onSuccess(response: MainResponse)
        fun onFailure(errorMessage: String)
    }
  interface PrivRankCallback {
        fun onSuccess(response: PrivRankResponse)
        fun onFailure(errorMessage: String)
    }

    interface MultiMallCallback {
        fun onSuccess(response: MultiMallResponse)
        fun onFailure(errorMessage: String)
    }


    interface DeviceTokenCallback {
        fun onSuccess()
        fun onFailure(errorMessage: String)
    }

    interface InAppAlertCallback {
        fun onSuccess(alerts: InAppAlertResponse)
        fun onFailure(errorMessage: String)
    }

    interface PayablePointsCallback {
        fun onSuccess()
        fun onFailure(errorMessage: String)
    }


    interface VersioningCallback {
        fun onSuccess(response: VersionChecking)
        fun onFailure(errorMessage: String)
    }

    interface DailyRewardsCallback {
        fun onSuccess(response: DailyRewardResponse)
        fun onFailure(errorMessage: String)
    }

    interface CheckInCallback {
        fun onSuccess(response: DailyCheckInResponse)
        fun onFailure(errorMessage: String)
    }


    interface WalletDetailCallback {
        fun onSuccess(response: WalletDetailResponse)
        fun onFailure(errorMessage: String)
    }

    interface WalletCallback {
        fun onSuccess(response: WalletResponse)
        fun onFailure(errorMessage: String)
    }

    interface VerifyPasswordCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
    }

    interface PickupCheckoutCallback {
        fun onSuccess(response: CheckOutResponse)
        fun onFailure(errorMessage: String)
    }

    interface RewardCheckoutCallback {
        fun onSuccess(response: CheckOutResponse)
        fun onFailure(errorMessage: String)
    }
    interface TimerRewardCallback {
        fun onSuccess(response: TimerResponse)
        fun onFailure(errorMessage: String)
    }

    interface ShippingPointCallback {
        fun onSuccess(response: ShippingPointResponse)
        fun onFailure(errorMessage: String)
    }
    interface getDirectoryListInRewardCallback {
        fun onSuccess(response: MerchantRewardResponse)
        fun onFailure(errorMessage: String)
    }

    interface getSaluationCallback {
        fun onSuccess(response: com.fireworks.myeventsdk.model.SalutationResponse)
        fun onFailure(errorMessage: String)
    }

    interface getSupportTypeCallback {
        fun onSuccess(response: com.fireworks.myeventsdk.model.SupportType)
        fun onFailure(errorMessage: String)
    }

    interface StatesCallback {
        fun onSuccess(response: StatesResponse)
        fun onFailure(errorMessage: String)
    }
    interface PurchaseCountCallBack {
        fun onSuccess(response: ApiResponse)
        fun onFailure(errorMessage: String)
    }

    interface RewardDetailCallback {
        fun onSuccess(response: RewardDetailResponse)
        fun onFailure(errorMessage: String)
    }

    interface RewardListCallback {
        fun onSuccess(response: RewardResponse)
        fun onFailure(errorMessage: String)
    }

    interface NearbyCategoryTypeCallback {
        fun onSuccess(response: RewardCategoryTypeResponse)
        fun onFailure(errorMessage: String)
    }

    interface RewardCategoryCallback {
        fun onSuccess(response: RewardCategoryResponse)
        fun onFailure(errorMessage: String)
    }

    interface RewardCategoryNewCallback {
        fun onSuccess(response: FilterData)
        fun onFailure(errorMessage: String)
    }

    interface DirectoryDetailCallback {
        fun onSuccess(response: DirectoryDetailResponse)
        fun onFailure(errorMessage: String)
    }

    interface DirectoryCallback {
        fun onSuccess(response: DirectoryResponse)
        fun onFailure(errorMessage: String)
    }

    interface DirectorySearchCallback {
        fun onSuccess(response: DirectoryResponse)
        fun onFailure(errorMessage: String)
    }

    interface DirectoryCategoryCallback {
        fun onSuccess(response: DirectoryCategoryResponse)
        fun onFailure(errorMessage: String)
    }

    interface DirectoryFloorCallback {
        fun onSuccess(response: DirectoryFloorResponse)
        fun onFailure(errorMessage: String)
    }

    interface FacilityCallback {
        fun onSuccess(response: FacilityResponse)
        fun onFailure(errorMessage: String)
    }

    interface FacilitySearchCallback {
        fun onSuccess(response: FacilityResponse)
        fun onFailure(errorMessage: String)
    }


    interface FacilityCategoryCallback {
        fun onSuccess(response: FacilityCategoryResponse)
        fun onFailure(errorMessage: String)
    }

    interface FacilityFloorCallback {
        fun onSuccess(response: FacilityFloorResponse)
        fun onFailure(errorMessage: String)
    }

    interface ReferralDataCallback {
        fun onSuccess(response: ReferralDataResponse)
        fun onFailure(errorMessage: String)
    }

    interface UsefulLinkCallback {
        fun onSuccess(response: UsefulLinksResponse)
        fun onFailure(errorMessage: String)
    }



    interface FacilityDetailCallback {
        fun onSuccess(response: FacilityDetailResponse)
        fun onFailure(errorMessage: String)
    }

    interface NewsDetailCallback {
        fun onSuccess(response: NewsDetailResponse)
        fun onFailure(errorMessage: String)
    }

    interface NewsListCallback {
        fun onSuccess(response: NewsResponse)
        fun onFailure(errorMessage: String)
    }
  interface GetArticleListCallback {
        fun onSuccess(response: ArticleListResponse)
        fun onFailure(errorMessage: String)
    }
  interface GetArticleCategoryCallback {
        fun onSuccess(response: ArticleCategoryResponse)
        fun onFailure(errorMessage: String)
    }
 interface GetArticleDetailsCallback {
        fun onSuccess(response: ArticleDetailResponse)
        fun onFailure(errorMessage: String)
    }


    interface NotificationCallback {
        fun onSuccess(response: NotificationResponse)
        fun onFailure(errorMessage: String)
    }

    interface ArchiveNotificationCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
        fun onTokenExpired(retry: () -> Unit)
    }

    interface PasswordUpdateCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
    }

    interface SetPasswordCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
    }


    interface SetVerifyPasswordCallback {
        fun onSuccess(response: VerifyOtpResponse)
        fun onFailure(errorMessage: String)
    }

    interface UploadImageCallback {
        fun onSuccess(response: UploadImageResponse)
        fun onFailure(errorMessage: String)
    }


    interface ScanReceiptCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
    }


    interface ShopCallback {
        fun onSuccess(response: ShopResponse)
        fun onFailure(error: String)
    }
    interface MerchantCateCallback {
        fun onSuccess(response: MerchantCategory)
        fun onFailure(error: String)
    }

    interface SettingsCallback {
        fun onSuccess(response: GetSettingsResponse)
        fun onFailure(errorMessage: String)
    }

    interface SettingsUpdateCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
    }

    interface SupportCallback {
        fun onSuccess(response: SupportResponse)
        fun onFailure(error: String)
    }

    interface TransactionCallback {
        fun onSuccess(response: TransactionResponse)
        fun onFailure(error: String)
    }


    interface TransactionDetailCallback {
        fun onSuccess(detail: TransactionDetailResponse)
        fun onFailure(error: String)
    }


    interface ReleasePointsCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
    }
 interface ClearNotCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
    }


    interface TransactionArchiveCallback {
        fun onSuccess(response: CommonResponse)
        fun onFailure(errorMessage: String)
    }

    interface MultiWalletCallback {
        fun onSuccess(response: WalletMultipleResponse)
        fun onFailure(errorMessage: String)
    }

    interface MigrateUserCallback {
        fun onSuccess(response: CheckMigrateUserResponse)
        fun onFailure(errorMessage: String)
    }

    interface MigratedDataCallback {
        fun onSuccess(response: MigratedUserDataResponse)
        fun onFailure(errorMessage: String)
    }


    interface RegisterCallback {
        fun onSuccess(response: RegisterResponse)
        fun onFailure(errorMessage: String)
    }

    interface TitleListCallback {
        fun onSuccess(response: HonorificListResponse)
        fun onFailure(errorMessage: String)
    }

    interface CountryListCallback {
        fun onSuccess(response: CountryListResponse)  // Replace with your actual model
        fun onFailure(errorMessage: String)
    }

    interface CountryCodesCallback {
        fun onSuccess(response: CountryCodesResponse)  // Replace with your actual model
        fun onFailure(errorMessage: String)
    }

    interface PointsCallback {
        fun onSuccess(response: PointDetailResponse)  // Replace with your actual model
        fun onFailure(errorMessage: String)
    }
//test
}