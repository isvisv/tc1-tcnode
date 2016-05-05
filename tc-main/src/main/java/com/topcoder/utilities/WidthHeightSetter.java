package com.topcoder.utilities;

import com.topcoder.shared.util.logging.Logger;
import com.topcoder.shared.util.DBMS;
import com.topcoder.shared.util.TCResourceBundle;
import com.topcoder.shared.util.sql.InformixSimpleDataSource;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * @author dok
 * @version $Revision: 61241 $ Date: 2005/01/01 00:00:00
 *          Create Date: May 21, 2007
 */
public class WidthHeightSetter {
    private static final Logger log = Logger.getLogger(WidthHeightSetter.class);
    public static void main(String[] args) {

        WidthHeightSetter w = new WidthHeightSetter();

        try {
            w.doit();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void doit() throws ClassNotFoundException, SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = new InformixSimpleDataSource(new TCResourceBundle("DBMS").getProperty("STUDIO_CONNECT_STRING")).getConnection();
            ps = conn.prepareStatement("update submission set width = ?, height = ? where submission_id = ?");
            for (int aCrap : crap) {
                doit1(ps, aCrap);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } finally {
            DBMS.close(ps);
            DBMS.close(conn);
        }
    }

    private void doit1(PreparedStatement ps, Integer id) throws IOException, URISyntaxException, SQLException {
        ImageInputStream iis = ImageIO.createImageInputStream(new URL("http://studio.topcoder.com/?module=DownloadSubmission&sbmid="+id).openStream());
        BufferedImage image = ImageIO.read(iis);

        ps.clearParameters();
        ps.setInt(1, image.getWidth());
        ps.setInt(2, image.getHeight());
        ps.setInt(3, id);
        ps.executeUpdate();

    }


    private int[] crap = {2156,
2157,
2164,
2167,
2176,
2177,
2178,
2179,
2180,
2181,
2183,
2186,
2188,
2191,
2193,
2195,
2214,
2239,
2240,
2241,
2242,
2243,
2244,
2250,
2256,
2257,
2260,
2261,
2262,
2263,
2273,
2274,
2275,
2276,
2277,
2279,
2292,
2293,
2299,
2306,
2318,
2320,
2321,
2322,
2324,
2325,
2331,
2332,
2365,
2366,
2367,
2372,
2373,
2374,
2375,
2376,
2380,
2433,
2434,
2435,
2436,
2437,
2438,
2439,
2463,
2464,
2465,
2466,
2467,
2481,
2489,
2490,
2522,
2523,
2527,
2528,
2529,
2547,
2562,
2575,
2576,
2577,
2613,
2614,
2615,
2616,
2617,
2618,
2619,
2620,
2621,
2622,
2623,
2624,
2625,
2650,
2651,
2652,
2653,
2666,
2667,
2668,
2669,
2682,
2683,
2720,
2721,
2722,
2725,
2727,
2728,
2738,
2739,
2740,
2749,
2760,
2761,
2762,
2768,
2769,
2770,
2771,
2773,
2778,
2779,
2780,
2781,
2782,
2783,
2784,
2785,
2786,
2787,
2788,
2789,
2794,
2795,
2796,
2797,
2798,
2801,
2802,
2823,
2825,
2826,
2867,
2871,
2872,
2873,
2874,
2875,
2881,
2883,
2884,
2885,
2888,
2889,
2894,
2895,
2899,
2900,
2902,
2903,
2904,
2905,
2906,
2952,
2960,
2962,
2963,
2964,
2965,
2966,
2967,
2968,
2969,
2970,
2975,
3012,
3013,
3023,
3025,
3026,
3049,
3050,
3051,
3052,
3079,
3080,
3086,
3087,
3088,
3099,
3132,
3133,
3134,
3135,
3136,
3137,
3138,
3141,
3144,
3145,
3146,
3147,
3148,
3149,
3150,
3151,
3152,
3161,
3162,
3163,
3164,
3205,
3225,
3228,
3241,
3242,
3245,
3246,
3254,
3255,
3268,
3273,
3275,
3276,
3277,
3278,
3285,
3291,
3292,
3293,
3295,
3296,
3297,
3429,
3436,
3442,
3443,
3449,
3452,
3453,
3455,
3456,
3457,
3459,
3460,
3464,
3465,
3466,
3467,
3471,
3472,
3473,
3474,
3478,
3479,
3482,
3483,
3490,
3491,
3494,
3502,
3503,
3509,
3511,
3512,
3521,
3549,
3550,
3556,
3560,
3561,
3562,
3563,
3564,
3565,
3566,
3567,
3568,
3569,
3570,
3571,
3578,
3579,
3580,
3581,
3582,
3610,
3613,
3614,
3615,
3617,
3618,
3619,
3650,
3653,
3659,
3660,
3661,
3670,
3671,
3672,
3673,
3674,
3675,
3676,
3678,
3681,
3682,
3683,
3684,
3685,
3686,
3687,
3688,
3694,
3695,
3696,
3697,
3698,
3706,
3707,
3708,
3720,
3721,
3723,
3724,
3762,
3763,
3764,
3765,
3766,
3768,
3769,
3770,
3771,
3772,
3773,
3774,
3775,
3776,
3777,
3778,
3779,
3780,
3781,
3782,
3783,
3784,
3785,
3786,
3787,
3789,
3794,
3796,
3798,
3799,
3800,
3801,
3802,
3803,
3804,
3805,
3807,
3822,
3826,
3827,
3828,
3829,
3833,
3834,
3835,
3836,
3837,
3838,
3845,
3846,
3849,
3854,
3855,
3857,
3858,
3859,
3862,
3863,
3864,
3865,
3880,
3881,
3882,
3883,
3885,
3889,
3890,
3891,
3892,
3906,
3926,
3927,
3932,
3933,
3934,
3935,
3954,
3955,
3959,
3960,
3961,
3962,
3965,
3966,
3967,
3981,
3983,
3984,
3985,
3986,
3987,
3988,
3994,
3997,
3998,
4003,
4004,
4005,
4006,
4007,
4010,
4011,
4012,
4013,
4015,
4019,
4021,
4022,
4023,
4028,
4032,
4033,
4037,
4038,
4039,
4044,
4046,
4047,
4048,
4049,
4051,
4052,
4053,
4054,
4055,
4056,
4057,
4119,
4126,
4127,
4131,
4132,
4148,
4152,
4154,
4155,
4156,
4157,
4158,
4159,
4163,
4164,
4165,
4166,
4173,
4175,
4176,
4177,
4178,
4179,
4180,
4181,
4182,
4186,
4187,
4188,
4189,
4190,
4212,
4216,
4232,
4251,
4252,
4253,
4254,
4256,
4259,
4260,
4261,
4262,
4263,
4264,
4265,
4266,
4267,
4269,
4271,
4273,
4274,
4275,
4276,
4277,
4278,
4279,
4280,
4281,
4282,
4283,
4284,
4285,
4286,
4287,
4295,
4296,
4301,
4302,
4303,
4304,
4305,
4306,
4307,
4308,
4309,
4310,
4312,
4313,
4314,
4315,
4316,
4321,
4322,
4323,
4324,
4325,
4326,
4327,
4328,
4329,
4341,
2154,
2158,
2159,
2160,
2161,
2162,
2163,
2175,
2182,
2184,
2185,
2187,
2189,
2190,
2192,
2194,
2196,
2197,
2198,
2200,
2201,
2202,
2203,
2204,
2205,
2206,
2207,
2208,
2209,
2210,
2211,
2212,
2215,
2216,
2217,
2218,
2235,
2236,
2237,
2238,
2245,
2246,
2248,
2249,
2258,
2269,
2270,
2271,
2281,
2282,
2283,
2284,
2285,
2286,
2287,
2288,
2294,
2315,
2316,
2328,
2330,
2333,
2357,
2361,
2362,
2379,
2381,
2412,
2413,
2414,
2415,
2418,
2419,
2420,
2421,
2425,
2441,
2442,
2471,
2472,
2473,
2474,
2493,
2510,
2511,
2512,
2513,
2514,
2524,
2563,
2564,
2565,
2566,
2567,
2568,
2569,
2570,
2571,
2572,
2573,
2591,
2592,
2593,
2594,
2595,
2596,
2597,
2598,
2599,
2600,
2601,
2602,
2603,
2606,
2607,
2608,
2609,
2610,
2611,
2633,
2634,
2635,
2636,
2637,
2638,
2654,
2655,
2656,
2688,
2689,
2690,
2691,
2692,
2697,
2700,
2702,
2706,
2707,
2708,
2710,
2711,
2712,
2713,
2714,
2726,
2729,
2763,
2790,
2791,
2792,
2793,
2800,
2840,
2841,
2842,
2843,
2844,
2845,
2846,
2863,
2865,
2870,
2891,
2892,
2893,
3048,
3053,
3058,
3108,
3109,
3110,
3111,
3112,
3113,
3114,
3115,
3116,
3117,
3194,
3195,
3203,
3256,
3257,
3258,
3259,
3260,
3261,
3262,
3263,
3264,
3265,
3266,
3267,
3269,
3270,
3271,
3272,
3434,
3480,
3481,
3489,
3523,
3524,
3525,
3612,
3649,
3651,
3677,
3850,
3851,
3852,
3853,
3916,
3921,
3922,
3923,
3924,
3925,
3964,
4050,
4128,
4129,
4130,
4149,
4150,
4183,
2150,
2151,
2152,
2153,
2155,
2165,
2166,
2168,
2169,
2170,
2171,
2172,
2173,
2174,
2199,
2213,
2224,
2225,
2226,
2227,
2228,
2229,
2230,
2231,
2232,
2233,
2252,
2264,
2266,
2295,
2297,
2311,
2312,
2319,
2323,
2356,
2358,
2359,
2360,
2382,
2432,
2457,
2459,
2460,
2461,
2462,
2507,
2508,
2509,
2515,
2516,
2517,
2518,
2519,
2520,
2549,
2550,
2551,
2552,
2553,
2554,
2555,
2556,
2557,
2558,
2559,
2560,
2561,
2588,
2632,
2640,
2641,
2642,
2643,
2644,
2645,
2646,
2647,
2648,
2649,
2675,
2684,
2685,
2701,
2723,
2724,
2750,
2751,
2752,
2753,
2754,
2755,
2756,
2759,
2764,
2765,
2766,
2767,
2772,
2774,
2776,
2777,
2812,
2886,
2887,
2890,
2956,
2957,
2958,
2959,
2971,
2972,
2973,
2974,
2981,
3015,
3037,
3038,
3039,
3040,
3041,
3042,
3043,
3044,
3045,
3047,
3057,
3059,
3060,
3061,
3062,
3063,
3064,
3065,
3066,
3067,
3068,
3100,
3101,
3102,
3103,
3154,
3155,
3156,
3157,
3188,
3189,
3190,
3191,
3279,
3280,
3281,
3282,
3283,
3284,
3288,
3289,
3290,
3294,
3427,
3428,
3431,
3444,
3445,
3446,
3447,
3448,
3450,
3451,
3454,
3458,
3463,
3469,
3475,
3476,
3477,
3484,
3485,
3486,
3487,
3488,
3492,
3493,
3496,
3497,
3498,
3504,
3505,
3506,
3539,
3540,
3541,
3544,
3548,
3553,
3554,
3574,
3575,
3585,
3586,
3587,
3588,
3606,
3607,
3608,
3609,
3621,
3622,
3662,
3663,
3689,
3690,
3691,
3692,
3693,
3709,
3738,
3739,
3740,
3741,
3742,
3743,
3744,
3745,
3746,
3747,
3748,
3749,
3750,
3848,
3903,
3904,
3918,
3919,
3928,
3989,
3990,
3991,
3992,
3993,
4008,
4014,
4025,
4026,
4029,
4030,
4031,
4040,
4042,
4062,
4067,
4068,
4069,
4070,
4071,
4107,
4108,
4111,
4112,
4114,
4115,
4120,
4122,
4123,
4124,
4125,
4145,
4146,
4147,
4184,
4185,
3430,
3526,
3527,
3528,
3529,
3530,
3531,
3532,
3536,
3576,
3589,
3590,
3591,
3592,
3593,
3594,
3595,
3596,
3597,
3598,
3599,
3626,
3627,
3664,
3665,
3666,
3667,
3668,
3669,
3699,
3705,
3725,
3726,
3727,
3733,
3734,
3735,
3736,
3737,
3767,
3808,
3809,
3810,
3811,
3812,
3813,
3814,
3815,
3816,
3817,
3818,
3819,
3820,
3821,
3839,
3842,
3871,
3872,
3873,
3874,
3877,
3878,
3884,
3886,
3887,
3888,
3905,
3912,
3913,
3914,
3956,
3957,
3958,
3963,
2219,
2220,
2221,
2222,
2223,
2234,
2247,
2251,
2253,
2255,
2259,
2265,
2267,
2268,
2272,
2278,
2289,
2290,
2291,
2296,
2298,
2300,
2301,
2302,
2303,
2304,
2307,
2308,
2309,
2313,
2314,
2317,
2326,
2327,
2329,
2348,
2349,
2350,
2351,
2352,
2353,
2364,
2384,
2385,
2386,
2387,
2388,
2410,
2416,
2417,
2422,
2423,
2431,
2440,
2450,
2451,
2452,
2453,
2454,
2455,
2456,
2458,
2468,
2469,
2477,
2478,
2479,
2480,
2484,
2485,
2486,
2494,
2495,
2496,
2497,
2498,
2499,
2500,
2501,
2502,
2503,
2504,
2505,
2506,
2521,
2525,
2526,
2530,
2531,
2532,
2533,
2534,
2535,
2536,
2537,
2538,
2539,
2540,
2541,
2542,
2543,
2544,
2545,
2546,
2548,
2574,
2578,
2579,
2580,
2581,
2582,
2583,
2584,
2586,
2587,
2589,
2604,
2626,
2627,
2628,
2629,
2630,
2631,
2670,
2671,
2672,
2673,
2676,
2677,
2678,
2679,
2680,
2681,
2686,
2687,
2693,
2694,
2695,
2696,
2698,
2699,
2703,
2704,
2705,
2709,
2715,
2716,
2717,
2718,
2719,
2730,
2731,
2732,
2733,
2734,
2735,
2736,
2737,
2741,
2742,
2743,
2744,
2745,
2746,
2747,
2748,
2757,
2758,
2775,
2799,
2808,
2809,
2810,
2811,
2813,
2814,
2815,
2816,
2817,
2818,
2819,
2820,
2821,
2822,
2824,
2827,
2828,
2829,
2830,
2831,
2832,
2833,
2834,
2835,
2836,
2837,
2838,
2839,
2847,
2848,
2849,
2850,
2851,
2852,
2853,
2854,
2855,
2856,
2857,
2858,
2859,
2860,
2861,
2862,
2864,
2866,
2876,
2877,
2878,
2879,
2880,
2882,
2907,
2908,
2909,
2976,
2977,
2978,
2979,
2980,
2982,
2983,
2984,
2985,
2986,
2987,
2988,
2989,
2990,
2991,
2992,
2993,
2994,
2995,
2996,
2997,
2998,
2999,
3000,
3001,
3002,
3003,
3004,
3005,
3006,
3007,
3008,
3009,
3010,
3011,
3014,
3019,
3020,
3021,
3022,
3024,
3027,
3028,
3029,
3030,
3031,
3032,
3033,
3034,
3035,
3036,
3046,
3054,
3055,
3056,
3069,
3070,
3071,
3072,
3073,
3074,
3075,
3076,
3077,
3078,
3081,
3082,
3083,
3084,
3085,
3089,
3090,
3091,
3092,
3093,
3094,
3095,
3096,
3097,
3098,
3104,
3105,
3106,
3107,
3118,
3119,
3126,
3127,
3128,
3129,
3130,
3131,
3165,
3166,
3167,
3168,
3169,
3170,
3171,
3172,
3173,
3174,
3175,
3176,
3177,
3178,
3179,
3180,
3181,
3182,
3183,
3184,
3187,
3192,
3193,
3196,
3197,
3198,
3199,
3200,
3202,
3204,
3206,
3207,
3208,
3209,
3210,
3211,
3212,
3213,
3214,
3215,
3216,
3217,
3218,
3219,
3220,
3221,
3222,
3223,
3224,
3226,
3227,
3229,
3230,
3231,
3232,
3233,
3234,
3235,
3236,
3237,
3238,
3239,
3240,
3243,
3244,
3247,
3248,
3253,
3286,
3287,
3437,
3438,
3439,
3440,
3441,
3461,
3462,
3495,
3499,
3500,
3501,
3507,
3508,
3510,
3516,
3517,
3518,
3519,
3520,
3522,
3533,
3534,
3537,
3538,
3542,
3543,
3547,
3557,
3558,
3559,
3572,
3573,
3577,
3583,
3601,
3602,
3603,
3604,
3605,
3611,
3616,
3620,
3623,
3624,
3625,
3628,
3629,
3630,
3631,
3632,
3633,
3634,
3635,
3636,
3637,
3638,
3639,
3640,
3641,
3642,
3643,
3644,
3645,
3646,
3647,
3648,
3652,
3654,
3655,
3656,
3657,
3658,
3679,
3680,
3700,
3701,
3702,
3703,
3704,
3710,
3711,
3712,
3713,
3714,
3715,
3716,
3717,
3718,
3719,
3722,
3730,
3731,
3732,
3751,
3752,
3753,
3754,
3755,
3756,
3757,
3758,
3790,
3791,
3792,
3793,
3795,
3797,
3806,
3823,
3824,
3825,
3830,
3831,
3832,
3840,
3841,
3843,
3844,
3847,
3856,
3860,
3861,
3866,
3867,
3868,
3869,
3870,
3875,
3876,
3879,
3893,
3894,
3895,
3896,
3897,
3898,
3899,
3900,
3901,
3902,
3915,
3917,
3920,
3929,
3930,
3931,
3936,
3937,
3938,
3939,
3940,
3941,
3942,
3943,
3944,
3945,
3946,
3947,
3948,
3949,
3950,
3951,
3952,
3953,
3968,
3969,
3970,
3971,
3972,
3973,
3974,
3975,
3976,
3977,
3978,
3979,
3980,
3995,
3996,
3999,
4000,
4001,
4002,
4009,
4016,
4017,
4018,
4020,
4024,
4027,
4034,
4035,
4036,
4041,
4043,
4045,
4059,
4060,
4063,
4064,
4074,
4075,
4095,
4100,
4101,
4102,
4103,
4104,
4106,
4109,
4113,
4116,
4117,
4118,
4135,
4138,
4144,
4151,
4153,
4160,
4161,
4162,
4167,
4170,
4171,
4172,
4174,
4191,
4192,
4193,
4194,
4195,
4196,
4197,
4198,
4199,
4200,
4201,
4202,
4213,
4214,
4215,
4236,
4237,
4238,
4239,
4240,
4241,
4242,
4243,
4244,
4245,
4246,
4247,
4248,
4249,
4250,
4255,
4257,
4258,
4290,
4291,
4292,
4293,
4294,
4297,
4298,
4299,
4300,
4330,
4331,
4332,
4333,
4334,
4335,
4336,
4337,
4338,
4342,
4343,
2355,
2370,
2371,
2383,
2394,
2430,
2447,
2470,
2475,
2476,
2482,
2487,
2491,
2492,
2590,
2612,
2639,
2657,
2658,
2659,
2660,
2661,
2662,
2663,
2664,
2665,
2803,
2804,
2805,
2806,
2807,
2868,
2869,
2896,
2897,
2898,
2901,
2953,
2954,
2955,
2961,
3016,
3017,
3018,
3120,
3121,
3122,
3123,
3124,
3125,
3139,
3140,
3142,
3143,
3153,
3158,
3159,
3160,
3185,
3186,
3201,
3249,
3250,
3251,
3252,
3274,
3432,
3433,
3435,
3468,
3470,
3513,
3514,
3515,
3535,
3545,
3546,
3551,
3552,
3555,
3584,
3600,
3728,
3729,
3759,
3760,
3761,
3788,
3907,
3908,
3909,
3910,
3911,
3982,
4110,
4133,
4134,
4168,
4169,
4203,
4204,
4205,
4206,
4207,
4208,
4209,
4210,
4211    };




}
