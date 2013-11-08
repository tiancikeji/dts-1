	var setting = {
		view : {
			showIcon : showIconForTree
		},
		data : {
			simpleData : {
				enable : true
			}
		}
	};
	var zNodes = [ {
		id : 1,
		pId : 0,
		name : "����n",
		open : true
	}, {
		id : 11,
		pId : 1,
		name : "���¹�n"
	}, {
		id : 111,
		pId : 11,
		name : "����n"
	}, {
		id : 112,
		pId : 11,
		name : "����n"
	}, {
		id : 113,
		pId : 11,
		name : "����n"
	}, {
		id : 114,
		pId : 11,
		name : "����n"
	}, {
		id : 12,
		pId : 1,
		name : "���¹�w"
	}, {
		id : 121,
		pId : 12,
		name : "����n"
	}, {
		id : 122,
		pId : 12,
		name : "����n"
	}, {
		id : 123,
		pId : 12,
		name : "����n"
	}, {
		id : 124,
		pId : 12,
		name : "����n"
	}, {
		id : 13,
		pId : 1,
		name : "���¹�n",
		isParent : true
	}, ];

	function showIconForTree(treeId, treeNode) {
		return !treeNode.isParent;
	};

	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});