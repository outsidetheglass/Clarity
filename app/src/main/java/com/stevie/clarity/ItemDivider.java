// ItemDivider.java
// Class that defines dividers displayed between the RecyclerView items;
// based on Google's sample implementation at bit.ly/DividerItemDecoration
package com.stevie.clarity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class ItemDivider extends RecyclerView.ItemDecoration {
   private final Drawable divider;

   // loads list item divider
   public ItemDivider(Context context) {
      int[] attrs = {android.R.attr.listDivider};
      divider = context.obtainStyledAttributes(attrs).getDrawable(0);
   }

   // draws the list item dividers into the RecyclerView
   @Override
   public void onDrawOver(Canvas c, RecyclerView parent,
      RecyclerView.State state) {
      super.onDrawOver(c, parent, state);

      // calculate x-coords for all dividers
      int left = parent.getPaddingLeft();
      int right = parent.getWidth() - parent.getPaddingRight();

      // draw a line below every item except last
      for (int i = 0; i < parent.getChildCount() - 1; ++i) {
         View item = parent.getChildAt(i); // get ith list item

         // calculate y-coords for the present divider
         int top = item.getBottom() + ((RecyclerView.LayoutParams)
            item.getLayoutParams()).bottomMargin;
         int bottom = top + divider.getIntrinsicHeight();

         // draw the divider with its bounds
         divider.setBounds(left, top, right, bottom);
         divider.draw(c);
      }
   }
}