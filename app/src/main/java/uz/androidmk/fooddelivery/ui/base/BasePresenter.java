package uz.androidmk.fooddelivery.ui.base;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import uz.androidmk.fooddelivery.data.DataManager;

/**
 * Created by Azamat on 8/8/2018.
 */

// this is base presenter type MvpView, it means it will be attached to Base activity
public class BasePresenter<V extends MvpView> implements MvpPresenter<V> {

    private V mMvpView;

    private DataManager mDataManager;

    private CompositeDisposable mCompositeDisposable;

    @Inject
    public BasePresenter(DataManager dataManager, CompositeDisposable compositeDisposable){
        mDataManager = dataManager;
        mCompositeDisposable = compositeDisposable;
    }
    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        if(mMvpView != null)
            mMvpView = null;
        mCompositeDisposable.dispose();
    }

    // this method is the most important method in basePresenter
    // it is a bridge between presenter and view
    // any subclass presenters can call this method
    // TO HAVE REFERENCE TO THE ATTACHED VIEW
    public V getMvpView(){
        return mMvpView;
    }

    public boolean isViewAttached(){
        return mMvpView != null;
    }

    public void checkViewAttached() {
        if(!isViewAttached())
            throw new PresenterNotAttachedToView();
    }


    public static class PresenterNotAttachedToView extends RuntimeException {
        public PresenterNotAttachedToView() {
            super("The presenter should be attached to View " +
                    "\n with onAttach(mvpView)");
        }
    }
}
