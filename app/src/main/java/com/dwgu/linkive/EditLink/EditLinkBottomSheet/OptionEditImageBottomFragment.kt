package com.dwgu.linkive.EditLink.EditLinkBottomSheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dwgu.linkive.databinding.FragmentOptionEditImageBottomBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

// 링크 편집 페이지 > 이미지 아이템 옵션 BottomSheet
class OptionEditImageBottomFragment : BottomSheetDialogFragment() {

    // ViewBinding Setting
    lateinit var binding: FragmentOptionEditImageBottomBinding

    // 리사이클러뷰에서의 position 값
    final val POSITION_IN_RECYCLERVIEW = "position_in_recyclerview"

    var position: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentOptionEditImageBottomBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 리사이클러뷰에서의 position 값
        position = arguments?.getString(POSITION_IN_RECYCLERVIEW)

        // 이미지 설정/변경 버튼 선택 시
        binding.relativelayoutSetImage.setOnClickListener(View.OnClickListener {
            dismiss()

            // 갤러리 열어서 이미지 선택
        })

        // 아이템 삭제 버튼 선택 시 -> 삭제 확인 BottomSheet
        binding.relativelayoutDeleteEditItem.setOnClickListener(View.OnClickListener {
            dismiss()

            // 링크 편집 아이템 삭제 확인 BottomSheet 열기
            val bottomSheet = DeleteEditItemBottomFragment()

            // recycleview에서의 position 값 전달
            val bundle = Bundle()
            bundle.putString("position_in_recyclerview", position)
            bottomSheet.arguments = bundle

            bottomSheet.show(requireActivity().supportFragmentManager, bottomSheet.tag)
        })
    }
}