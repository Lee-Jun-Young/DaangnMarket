package com.smparkworld.daangnmarket.extension

import android.util.SparseArray
import android.view.MenuItem
import androidx.core.util.set
import androidx.core.view.forEachIndexed
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smparkworld.daangnmarket.R

/**
 *  BottomNavigationView의 메뉴 선택 시 Fragment의 View가 재생성되므로 show/hide처리를 위해
 * Extension function 작성. BottomNavigationView의 Menu xml 순서와 fragmentList의 순서가 일치해야 함.
 *
 * @param fragmentManager   Transaction 작성을 위한 FragmentManager 인스턴스
 * @param containerId       각 Fragment가 추가될 Container Resource ID
 * @param fragmentList      BottomNavigationView의 Menu xml에 맞게 보여줄 Fragment List
 *
 * @return BottomNavigationView의 MenuItem 선택 시 선택된 MenuItem으로 변경되는 LiveData 인스턴스
 */
@Suppress("UNREACHABLE_CODE")
fun BottomNavigationView.setupWithNavController(
    fragmentManager: FragmentManager,
    containerId: Int,
    fragmentList: List<Fragment>
): LiveData<MenuItem>? {

    if (menu.size() != fragmentList.size) {
        throw IllegalArgumentException("Menu size and fragment list size do not match.")
        return null
    }

    val tagMap = SparseArray<String>()
    val selectedMenuItem = MutableLiveData<MenuItem>()
    selectedMenuItem.value = menu.findItem(selectedItemId)

    val tagPrefix = "btmNavFrg#"
    menu.forEachIndexed { index, item -> tagMap[item.itemId] = tagPrefix + index }

    // Android System에서 Activity를 재생성 했을 경우 Fragment 중복 추가 방지
    if (fragmentManager.findFragmentByTag("btmNavFrg#0") == null) {
        fragmentList.forEachIndexed { index, frg ->
            fragmentManager.beginTransaction().add(containerId, frg, tagPrefix + index).commitNow()
            if (tagMap[selectedItemId] == frg.tag) {
                fragmentManager.beginTransaction().show(frg).commitNow()
            } else {
                fragmentManager.beginTransaction().hide(frg).commitNow()
            }
        }
    }

    setOnNavigationItemSelectedListener { item ->
        if (!fragmentManager.isStateSaved) {
            if (selectedItemId != item.itemId) {
                val newlySelectedTag = tagMap[item.itemId]
                val newlySelectedFragment = fragmentManager.findFragmentByTag(newlySelectedTag)
                if (newlySelectedFragment != null) {
                    fragmentManager.beginTransaction()
                        .setCustomAnimations(
                            R.anim.nav_default_enter_anim,
                            R.anim.nav_default_exit_anim,
                            R.anim.nav_default_pop_enter_anim,
                            R.anim.nav_default_pop_exit_anim)
                        .show(newlySelectedFragment)
                        .apply {
                            hide(fragmentManager.findFragmentByTag(tagMap[selectedItemId])!!)
                        }
                        .setReorderingAllowed(true)
                        .commit()
                    selectedMenuItem.value = item
                    return@setOnNavigationItemSelectedListener true
                }
            }
        }
        false
    }
    return selectedMenuItem
}