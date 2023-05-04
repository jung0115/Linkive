package com.dwgu.linkive.LinkView

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dwgu.linkive.LinkView.LinkViewBottomSheet.ManageLinkBottomFragment
import com.dwgu.linkive.LinkView.LinkViewBottomSheet.SelectPagesheetBottomFragment
import com.dwgu.linkive.LinkView.LinkViewRecycler.*
import com.dwgu.linkive.R
import com.dwgu.linkive.databinding.FragmentLinkViewBinding


class LinkViewFragment : Fragment() {

    // ViewBinding Setting
    lateinit var binding: FragmentLinkViewBinding

    // 링크 View 페이지 아이템 Recyclerview
    private val linkViewItems = mutableListOf<LinkViewItem>()
    private lateinit var linkViewAdapter: LinkViewAdapter

    // PageSheet 선택 정보
    private var pageSheet: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLinkViewBinding.inflate(layoutInflater)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 페이지 정보 세팅 - 제목, 폴더, 출처 플랫폼
        setLinkViewInformation("제목입니다", "instagram", "폴더1")

        //pageSheet = "oo"

        // PageSheet 미선택 상태일 때
        if(pageSheet == null) {
            binding.linearlayoutUnselectPagesheet.visibility = View.VISIBLE
        }

        // 해당 링크 페이지의 url - 임시 테스트 데이터
        var linkUrl = "https://github.com/jung0115/Linkive_AOS"

        // recyclerview 세팅
        initRecycler()

        // 테스트 데이터
        addLinkViewItem(LinkViewImageItem("https:/img.youtube.com/vi/UYGud3qJeFI/default.jpg"))
        /*addLinkViewItem(LinkViewTextItem("글 테스트입니다.\n테스트 글입니다."))
        addLinkViewItem(LinkViewTextItem(null))
        addLinkViewItem(LinkViewPlaceItem("서울 송파구 올림픽로 240", "잠실동 40-1"))
        addLinkViewItem(LinkViewPlaceItem(null, null))
        addLinkViewItem(LinkViewLinkItem("백준 - 토마토(7569)", "https://www.acmicpc.net/problem/7569"))
        addLinkViewItem(LinkViewLinkItem(null, null))
        addLinkViewItem(LinkViewCodeItem("System.out.print(“Hello, World!”);\n\n" +
                "while(i < 10) {\ni++;\n}\n\nSystem.out.print(“Hello, World!”);\nSystem.out.print(“Hello, World!”);"))
        addLinkViewItem(LinkViewCodeItem(null))
        addLinkViewItem(LinkViewCheckboxItem("할 일 1", true))
        addLinkViewItem(LinkViewCheckboxItem(null, true))
        addLinkViewItem(LinkViewCheckboxItem("할 일 2", false))
        addLinkViewItem(LinkViewImageItem("https:/img.youtube.com/vi/UYGud3qJeFI/default.jpg"))
        addLinkViewItem(LinkViewTextItem("글 테스트입니다.\n테스트 글입니다."))*/

        // 제목 오른쪽 점 3개 버튼 선택 시 BottomSheet 나오게
        binding.btnLinkViewManage.setOnClickListener {
            val bottomSheet = ManageLinkBottomFragment()

            // 해당 링크 페이지의 url 값 전달
            val bundle = Bundle()
            bundle.putString("url_of_link_memo", linkUrl)
            bottomSheet.arguments = bundle

            bottomSheet.show(requireActivity().supportFragmentManager, bottomSheet.tag)
        }

        // PageSheet 미선택 상태에서 PageSheet 선택 버튼
        binding.btnSelectPagesheet.setOnClickListener {
            val bottomSheet = SelectPagesheetBottomFragment()
            bottomSheet.show(requireActivity().supportFragmentManager, bottomSheet.tag)
        }
    }

    // 페이지 정보 세팅 - 제목, 폴더, 출처 플랫폼
    private fun setLinkViewInformation(title: String, source: String?, folder: String?) {
        // 제목
        binding.textviewLinkViewTitle.text = title

        // 출처 플랫폼
        // 출처 플랫폼이 존재하는 경우
        if(source != null) {
            val iconResourceName = "ic_link_list_item_source_" + source
            val iconResourceId = resources.getIdentifier(iconResourceName, "drawable", requireContext().packageName)
            binding.imgLinkViewSource.setImageResource(iconResourceId)
        }

        // 폴더
        // 폴더가 존재하는 경우
        if(folder != null) {
            binding.imgLinkViewFolder.setImageResource(R.drawable.ic_folder_exist) // 폴더 존재 아이콘
            binding.textviewLinkViewFolder.text = folder                           // 폴더명
        }
    }

    // 링크 View 페이지 아이템 recyclerview 세팅
    private fun initRecycler() {
        linkViewAdapter = LinkViewAdapter(requireContext())
        binding.recyclerviewLinkView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewLinkView.adapter = linkViewAdapter
        binding.recyclerviewLinkView.isNestedScrollingEnabled = false // 스크롤을 매끄럽게 해줌
        linkViewAdapter.items = linkViewItems
    }

    // 링크 View 페이지 아이템 추가
    private fun addLinkViewItem(item: LinkViewItem) {
        linkViewItems.apply {
            add(item)
        }
        linkViewAdapter.notifyDataSetChanged()
    }
}