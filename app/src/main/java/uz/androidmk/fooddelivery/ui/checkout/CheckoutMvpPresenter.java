package uz.androidmk.fooddelivery.ui.checkout;


import uz.androidmk.fooddelivery.ui.base.MvpPresenter;

/**
 * Created by Azamat on 9/12/2018.
 */

public interface CheckoutMvpPresenter<V extends CheckoutMvpView> extends MvpPresenter<V> {

    void onRequestFoodList();

}
