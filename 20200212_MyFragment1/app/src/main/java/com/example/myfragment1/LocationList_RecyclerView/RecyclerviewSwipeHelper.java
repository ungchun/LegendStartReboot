package com.example.myfragment1.LocationList_RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

enum ButtonsState {
    GONE,
    LEFT_VISIBLE,
    RIGHT_VISIBLE;
}

public class RecyclerviewSwipeHelper extends ItemTouchHelper.Callback {

    private SwipeActionInterface buttonsActions = null;
    private boolean swipeBack = false;
    private ButtonsState buttonShowedState = ButtonsState.GONE;
    private static final float buttonWidth = 200;
    private RectF buttonInstance = null;
    private RecyclerView.ViewHolder currentItemViewHolder = null;
    private int firstSwipeFlag = 0;
    private Context context = null;
    private String sorting = null;
    private RecyclerAdapter recyclerAdapter = null;
    private LocationViewModel locationViewModel;

    RecyclerviewSecondSwipeToDoHelper recyclerviewSecondSwipeToDoHelper = null;
    RecyclerviewSecondSwipeDismissHelper recyclerviewSecondSwipeDismissHelper = null;
    private ItemTouchHelper itemTouchHelperRight = null;
    private ItemTouchHelper itemTouchHelperLeft = null;

    private RecyclerView recyclerView = null;

    public void setRecyclerView(RecyclerView recyclerView){
        this.recyclerView = recyclerView;
    }
    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int drag_flags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipe_flags = ItemTouchHelper.START | ItemTouchHelper.END;
        return makeMovementFlags(drag_flags, swipe_flags);
    }
    public void getButtonGone(boolean flag){
        if(flag){
            buttonShowedState = ButtonsState.GONE;
        }
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

        return false;
    }


    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    public RecyclerviewSwipeHelper(Context context, LocationViewModel locationViewModel, RecyclerAdapter recyclerAdapter,SwipeActionInterface buttonsActions) {
        this.buttonsActions = buttonsActions;
        this.context = context;
        this.recyclerAdapter = recyclerAdapter;
        this.locationViewModel = locationViewModel;
        RecyclerviewSecondSwipeToDoHelper recyclerviewSecondSwipeToDoHelper = new RecyclerviewSecondSwipeToDoHelper(0, ItemTouchHelper.RIGHT);
        RecyclerviewSecondSwipeDismissHelper recyclerviewSecondSwipeDismissHelper = new RecyclerviewSecondSwipeDismissHelper(0, ItemTouchHelper.LEFT, recyclerAdapter, locationViewModel);
        itemTouchHelperRight = new ItemTouchHelper(recyclerviewSecondSwipeToDoHelper);
        itemTouchHelperLeft = new ItemTouchHelper(recyclerviewSecondSwipeDismissHelper);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        if (swipeBack) {
            swipeBack = buttonShowedState != ButtonsState.GONE;
            return 0;
        }
        return super.convertToAbsoluteDirection(flags, layoutDirection);
    }



    public void onDraw(Canvas c) {
        if (currentItemViewHolder != null) {
            drawButtons(c, currentItemViewHolder);
        }
    }
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
            if (buttonShowedState != ButtonsState.GONE) {
                if (buttonShowedState == ButtonsState.LEFT_VISIBLE) {
                    firstSwipeFlag = ItemTouchHelper.RIGHT;
                    dX = Math.max(dX, buttonWidth);
                }
                if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) {
                    dX = Math.min(dX, -buttonWidth);
                    firstSwipeFlag = ItemTouchHelper.LEFT;
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            } else {
                setTouchListner(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            }
        }

        if (buttonShowedState == ButtonsState.GONE) {
            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
        currentItemViewHolder = viewHolder;

    }

    public RecyclerView.ViewHolder setButtonClickNumber(RecyclerView.ViewHolder viewHolder){
        return viewHolder;
    }

    //Button의 모양
    private void drawButtons(Canvas c, RecyclerView.ViewHolder viewHolder) {
        float buttonWidthWithoutPadding = buttonWidth - 10;
        float corners = 5;
        View itemView = viewHolder.itemView;
        Paint p = new Paint();

        buttonInstance = null;
        if (buttonShowedState == ButtonsState.LEFT_VISIBLE) {
            firstSwipeFlag = ItemTouchHelper.RIGHT;
            RectF leftButton = new RectF(itemView.getLeft(), itemView.getTop(), itemView.getLeft() + buttonWidthWithoutPadding, itemView.getBottom());
            p.setColor(Color.BLUE);
            c.drawRoundRect(leftButton, corners, corners, p);
            drawText("EDIT", c, leftButton, p);
            itemTouchHelperRight.attachToRecyclerView(recyclerView);
            itemTouchHelperLeft.attachToRecyclerView(null);

            buttonInstance = leftButton;
        } else if (buttonShowedState == ButtonsState.RIGHT_VISIBLE) {
            firstSwipeFlag = ItemTouchHelper.LEFT;
            RectF rightButton = new RectF(itemView.getRight() - buttonWidthWithoutPadding, itemView.getTop(), itemView.getRight(), itemView.getBottom());
            p.setColor(Color.RED);
            c.drawRoundRect(rightButton, corners, corners, p);
            drawText("DELETE", c, rightButton, p);

            itemTouchHelperLeft.attachToRecyclerView(recyclerView);
            itemTouchHelperRight.attachToRecyclerView(null);

            buttonInstance = rightButton;
        }
        if(buttonShowedState != null)
            setButtonClickNumber(viewHolder);
    }

    private void drawText(String text, Canvas c, RectF button, Paint p) {
        float textSize = 60;
        p.setColor(Color.WHITE);
        p.setAntiAlias(true);
        p.setTextSize(textSize);

        float textWidth = p.measureText(text);
        c.drawText(text, button.centerX() - (textWidth / 2), button.centerY() + (textSize / 2), p);
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setTouchListner(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {

        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                swipeBack = event.getAction() == MotionEvent.ACTION_CANCEL || event.getAction() == MotionEvent.ACTION_UP;
                if (swipeBack) {
                    if (dX < -buttonWidth) buttonShowedState = ButtonsState.RIGHT_VISIBLE;
                    else if (dX > buttonWidth) buttonShowedState = ButtonsState.LEFT_VISIBLE;
                    if (buttonShowedState != ButtonsState.GONE) {
                        setTouchDownListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                        setItemsClickable(recyclerView, false);
                    }
                }
                return false;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setTouchDownListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        Log.d("1","Start setTouchDownListener");
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                boolean e = buttonInstance.contains(event.getX(), event.getY());
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setTouchUpListener(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                }
                return false;
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setTouchUpListener(final Canvas c, final RecyclerView recyclerView, final RecyclerView.ViewHolder viewHolder, final float dX, final float dY, final int actionState, final boolean isCurrentlyActive) {
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    RecyclerviewSwipeHelper.super.onChildDraw(c, recyclerView, viewHolder, 0F, dY, actionState, isCurrentlyActive);
                    recyclerView.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View v, MotionEvent event) {
                            return false;
                        }
                    });
                    setItemsClickable(recyclerView, true);
                    swipeBack = false;

                    Log.d("tag","Interfacein");
                    if (buttonsActions != null && buttonInstance != null && buttonInstance.contains(event.getX(), event.getY())) {
                        if(buttonShowedState == ButtonsState.LEFT_VISIBLE){
                            recyclerviewSecondSwipeToDoHelper = new RecyclerviewSecondSwipeToDoHelper(0, ItemTouchHelper.RIGHT);
                            buttonsActions.onLeftClicked(viewHolder, viewHolder.getAdapterPosition(), recyclerviewSecondSwipeToDoHelper);
                        }else if (buttonShowedState == ButtonsState.RIGHT_VISIBLE){
                            recyclerviewSecondSwipeDismissHelper =
                                    new RecyclerviewSecondSwipeDismissHelper(0, ItemTouchHelper.LEFT, recyclerAdapter, locationViewModel);
                            buttonsActions.onRightClicked(viewHolder, viewHolder.getAdapterPosition(), recyclerviewSecondSwipeDismissHelper);
                        }
                    }
                    firstSwipeFlag = 0;
                    buttonShowedState = ButtonsState.GONE;
                    return false;
                }
                return false;
            }
        });
    }

    private void setItemsClickable(RecyclerView recyclerView, boolean isClickable) {
        Log.d("1","Start setItemsClickable");
        for (int i = 0; i < recyclerView.getChildCount(); ++i) {
            Log.d("1","setItemsClickable : " + i);
            recyclerView.getChildAt(i).setClickable(isClickable);
        }
    }
}
